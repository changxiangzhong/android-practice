package no.hyper.transcode;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Build;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * The elementary stream coming out of the "video/avc" encoder needs to be fed back into
 * the decoder one chunk at a time.  If we just wrote the data to a file, we would lose
 * the information about chunk boundaries.  This class stores the encoded data in memory,
 * retaining the chunk organization.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class VideoChunks {
    private static final String TAG = VideoChunks.class.getSimpleName();
    private MediaFormat mMediaFormat;
    private ArrayList<byte[]> mChunks = new ArrayList<byte[]>();
    private ArrayList<Integer> mFlags = new ArrayList<Integer>();
    private ArrayList<Long> mTimes = new ArrayList<Long>();

    /**
     * Sets the MediaFormat, for the benefit of a future decoder.
     */
    public void setMediaFormat(MediaFormat format) {
        mMediaFormat = format;
    }

    /**
     * Gets the MediaFormat that was used by the encoder.
     */
    public MediaFormat getMediaFormat() {
        return mMediaFormat;
    }

    /**
     * Adds a new chunk.  Advances buf.position to buf.limit.
     */
    public void addChunk(ByteBuffer buf, int flags, long time) {
        byte[] data = new byte[buf.remaining()];
        buf.get(data);
        mChunks.add(data);
        mFlags.add(flags);
        mTimes.add(time);
    }

    /**
     * Returns the number of chunks currently held.
     */
    public int getNumChunks() {
        return mChunks.size();
    }

    /**
     * Copies the data from chunk N into "dest".  Advances dest.position.
     */
    public void getChunkData(int chunk, ByteBuffer dest) {
        byte[] data = mChunks.get(chunk);
        dest.put(data);
    }

    /**
     * Returns the flags associated with chunk N.
     */
    public int getChunkFlags(int chunk) {
        return mFlags.get(chunk);
    }

    /**
     * Returns the timestamp associated with chunk N.
     */
    public long getChunkTime(int chunk) {
        return mTimes.get(chunk);
    }

    /**
     * Writes the chunks to a file as a contiguous stream.  Useful for debugging.
     */
    public void saveToFile(File file) {
        Log.d(TAG, "saving chunk data to file " + file);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            fos = null;     // closing bos will also close fos

            int numChunks = getNumChunks();
            for (int i = 0; i < numChunks; i++) {
                byte[] chunk = mChunks.get(i);
                bos.write(chunk);
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }

    public void saveMp4(File file) {
        try {
            __saveMp4(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void __saveMp4(File file) throws IOException {
        MediaMuxer muxer = new MediaMuxer(file.getAbsolutePath(), MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
        // MediaFormat audioFormat = new MediaFormat(); //TODO
        // MediaFormat videoFormat = new MediaFormat(); //TODO
        Log.v(TAG, "saving mp4 to file: " + file);
        int videoTrackIndex = muxer.addTrack(mMediaFormat);
        // int audioTrackIndex = muxer.addTrack(audioFormat);
        ByteBuffer inputBuffer  = ByteBuffer.allocate(1024 * 1024);

        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();

        muxer.start();

        for (int i = 0 ; i < mChunks.size(); i++) {
            byte[] chunk = mChunks.get(i);
            inputBuffer.put(chunk);
            //noinspection WrongConstant
            bufferInfo.set(0, chunk.length, mTimes.get(i), mFlags.get(i));
            muxer.writeSampleData(videoTrackIndex, inputBuffer, bufferInfo);
            inputBuffer.clear();
        }

        muxer.stop();
        muxer.release();

    }
}
