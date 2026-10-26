package com.exteragram.messenger.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;

public class Mp4Fingerprint {
    public String audioHandler;
    public String audioLanguage;
    private int boxes;
    public final ArrayList<String> compatibleBrands = new ArrayList<>();
    public String encoder;
    public boolean fragmented;
    public String majorBrand;
    public long minorVersion;
    private boolean movie;
    public String videoHandler;
    public String videoLanguage;

    public static class Track {
        String language;
        boolean media;
        String name;
        String type;

        private Track() {
        }
    }

    public static Mp4Fingerprint parse(String str) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
            try {
                Mp4Fingerprint mp4Fingerprint = new Mp4Fingerprint();
                mp4Fingerprint.readBoxes(randomAccessFile, randomAccessFile.length(), _UrlKt.FRAGMENT_ENCODE_SET, null);
                if (mp4Fingerprint.majorBrand == null || !mp4Fingerprint.movie || mp4Fingerprint.videoHandler == null || mp4Fingerprint.audioHandler == null || mp4Fingerprint.videoLanguage == null || mp4Fingerprint.audioLanguage == null) {
                    mp4Fingerprint = null;
                }
                randomAccessFile.close();
                return mp4Fingerprint;
            } catch (Throwable th) {
                try {
                    randomAccessFile.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    private void readBoxes(RandomAccessFile randomAccessFile, long j, String str, Track track) throws IOException {
        int i;
        RandomAccessFile randomAccessFile2;
        Mp4Fingerprint mp4Fingerprint = this;
        RandomAccessFile randomAccessFile3 = randomAccessFile;
        while (randomAccessFile3.getFilePointer() < j) {
            long filePointer = randomAccessFile3.getFilePointer();
            int i2 = mp4Fingerprint.boxes + 1;
            mp4Fingerprint.boxes = i2;
            if (i2 <= 4096) {
                long j2 = j - filePointer;
                if (j2 >= 8) {
                    long j3 = ((long) randomAccessFile3.readInt()) & 4294967295L;
                    byte[] bArr = new byte[4];
                    randomAccessFile3.readFully(bArr);
                    Charset charset = StandardCharsets.ISO_8859_1;
                    String str2 = new String(bArr, charset);
                    if (j3 != 1) {
                        if (j3 == 0) {
                            j3 = j2;
                        }
                        i = 8;
                    } else {
                        if (j2 < 16) {
                            throw new IOException();
                        }
                        j3 = randomAccessFile3.readLong();
                        i = 16;
                    }
                    long j4 = i;
                    if (j3 < j4 || j3 > j2) {
                        throw new IOException();
                    }
                    long j5 = filePointer + j3;
                    long j6 = j3 - j4;
                    if (str2.equals("ftyp") && str.isEmpty()) {
                        if (mp4Fingerprint.majorBrand != null || j6 < 8 || j6 > 256 || j6 % 4 != 0) {
                            throw new IOException();
                        }
                        randomAccessFile3.readFully(bArr);
                        mp4Fingerprint.majorBrand = new String(bArr, charset);
                        mp4Fingerprint.minorVersion = ((long) randomAccessFile3.readInt()) & 4294967295L;
                        while (randomAccessFile3.getFilePointer() < j5) {
                            randomAccessFile3.readFully(bArr);
                            mp4Fingerprint.compatibleBrands.add(new String(bArr, StandardCharsets.ISO_8859_1));
                        }
                    } else {
                        if (str2.equals("moov") && str.isEmpty()) {
                            if (mp4Fingerprint.movie) {
                                throw new IOException();
                            }
                            mp4Fingerprint.movie = true;
                            mp4Fingerprint.readBoxes(randomAccessFile3, j5, str2, null);
                            mp4Fingerprint = this;
                        } else {
                            if (str2.equals("trak") && str.equals("moov")) {
                                Track track2 = new Track();
                                readBoxes(randomAccessFile, j5, str2, track2);
                                if ("vide".equals(track2.type)) {
                                    if (this.videoHandler != null) {
                                        throw new IOException();
                                    }
                                    this.videoHandler = track2.name;
                                    this.videoLanguage = track2.language;
                                } else if ("soun".equals(track2.type)) {
                                    if (this.audioHandler != null) {
                                        throw new IOException();
                                    }
                                    this.audioHandler = track2.name;
                                    this.audioLanguage = track2.language;
                                }
                                mp4Fingerprint = this;
                                randomAccessFile2 = randomAccessFile;
                            } else if (str2.equals("mdia") && str.equals("trak")) {
                                if (track.media) {
                                    throw new IOException();
                                }
                                track.media = true;
                                mp4Fingerprint = this;
                                randomAccessFile3 = randomAccessFile;
                                mp4Fingerprint.readBoxes(randomAccessFile3, j5, str2, track);
                            } else {
                                randomAccessFile3 = randomAccessFile;
                                if (str2.equals("hdlr") && str.equals("mdia")) {
                                    if (track.type != null || j6 < 24 || j6 > 256 || randomAccessFile3.readInt() != 0) {
                                        throw new IOException();
                                    }
                                    randomAccessFile3.readInt();
                                    randomAccessFile3.readFully(bArr);
                                    track.type = new String(bArr, charset);
                                    randomAccessFile3.skipBytes(12);
                                    int i3 = ((int) j6) - 24;
                                    byte[] bArr2 = new byte[i3];
                                    randomAccessFile3.readFully(bArr2);
                                    int i4 = 0;
                                    while (i4 < i3 && bArr2[i4] != 0) {
                                        i4++;
                                    }
                                    track.name = new String(bArr2, 0, i4, StandardCharsets.UTF_8);
                                } else {
                                    if (str2.equals("mdhd") && str.equals("mdia")) {
                                        if (track.language != null || j6 < 24) {
                                            throw new IOException();
                                        }
                                        int i5 = randomAccessFile3.readInt();
                                        if (i5 == 0 || i5 == 16777216) {
                                            if (j6 >= (i5 != 0 ? 36 : 24)) {
                                                randomAccessFile3.skipBytes(i5 != 0 ? 28 : 16);
                                                int unsignedShort = randomAccessFile3.readUnsignedShort();
                                                track.language = new String(new char[]{(char) (((unsignedShort >> 10) & 31) + 96), (char) (((unsignedShort >> 5) & 31) + 96), (char) ((unsignedShort & 31) + 96)});
                                            }
                                        }
                                        throw new IOException();
                                    }
                                    if (str2.equals("udta") && str.equals("moov")) {
                                        mp4Fingerprint = this;
                                        mp4Fingerprint.readBoxes(randomAccessFile3, j5, str2, null);
                                    } else if (str2.equals("meta") && (str.equals("udta") || str.equals("moov"))) {
                                        if (j6 < 4 || randomAccessFile.readInt() != 0) {
                                            throw new IOException();
                                        }
                                        mp4Fingerprint = this;
                                        mp4Fingerprint.readBoxes(randomAccessFile, j5, str2, null);
                                    } else if ((str2.equals("ilst") && str.equals("meta")) || (str2.equals("©too") && str.equals("ilst"))) {
                                        mp4Fingerprint = this;
                                        randomAccessFile3 = randomAccessFile;
                                        mp4Fingerprint.readBoxes(randomAccessFile3, j5, str2, null);
                                    } else {
                                        mp4Fingerprint = this;
                                        j5 = j5;
                                        randomAccessFile2 = randomAccessFile;
                                        if (str2.equals("data") && str.equals("©too")) {
                                            if (mp4Fingerprint.encoder != null || j6 < 8 || j6 > 1024 || randomAccessFile2.readInt() != 1) {
                                                throw new IOException();
                                            }
                                            randomAccessFile2.readInt();
                                            byte[] bArr3 = new byte[((int) j6) - 8];
                                            randomAccessFile2.readFully(bArr3);
                                            mp4Fingerprint.encoder = new String(bArr3, StandardCharsets.UTF_8);
                                        } else if (str2.equals("mvex") || str2.equals("moof")) {
                                            mp4Fingerprint.fragmented = true;
                                        }
                                    }
                                }
                                mp4Fingerprint = this;
                            }
                            randomAccessFile2.seek(j5);
                            randomAccessFile3 = randomAccessFile2;
                        }
                        randomAccessFile2 = randomAccessFile;
                        randomAccessFile2.seek(j5);
                        randomAccessFile3 = randomAccessFile2;
                    }
                    j5 = j5;
                    randomAccessFile2 = randomAccessFile3;
                    randomAccessFile2.seek(j5);
                    randomAccessFile3 = randomAccessFile2;
                }
            }
            throw new IOException();
        }
    }

    public String describe() {
        return "ftyp=" + this.majorBrand + "/" + this.minorVersion + "/" + android.text.TextUtils.join(",", this.compatibleBrands) + " video=" + this.videoHandler + "/" + this.videoLanguage + " audio=" + this.audioHandler + "/" + this.audioLanguage + " encoder=" + this.encoder + " fragmented=" + this.fragmented;
    }
}
