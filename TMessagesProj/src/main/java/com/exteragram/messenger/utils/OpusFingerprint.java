package com.exteragram.messenger.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import kotlin.UByte;

public class OpusFingerprint {
    public final String vendor;

    private OpusFingerprint(String str) {
        this.vendor = str;
    }

    public static OpusFingerprint parse(String str) throws Throwable {
        OpusFingerprint opusFingerprint;
        ByteBuffer byteBufferOrder;
        int i;
        int i2;
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(str)));
            try {
                byte[] bArr = new byte[65536];
                byte[] bArr2 = new byte[255];
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                boolean z = false;
                while (i4 + 27 <= 262144) {
                    if (dataInputStream.readInt() == 1332176723 && dataInputStream.readUnsignedByte() == 0) {
                        int unsignedByte = dataInputStream.readUnsignedByte();
                        dataInputStream.readLong();
                        int i9 = dataInputStream.readInt();
                        opusFingerprint = null;
                        try {
                            int iReverseBytes = Integer.reverseBytes(dataInputStream.readInt());
                            dataInputStream.readInt();
                            int unsignedByte2 = dataInputStream.readUnsignedByte();
                            if (i4 == 0) {
                                if (unsignedByte == 2 && iReverseBytes == 0) {
                                    i5 = i9;
                                    if ((unsignedByte & 1) != i7 && (unsignedByte & (-8)) == 0) {
                                        i6 = iReverseBytes + 1;
                                        i4 += unsignedByte2 + 27;
                                        if (i4 <= 262144) {
                                            dataInputStream.readFully(bArr2, i3, unsignedByte2);
                                            int i10 = i3;
                                            while (i10 < unsignedByte2) {
                                                int i11 = bArr2[i10] & UByte.MAX_VALUE;
                                                int i12 = i8 + i11;
                                                if (i12 <= 65536 && (i4 = i4 + i11) <= 262144) {
                                                    dataInputStream.readFully(bArr, i8, i11);
                                                    i7 = i11 == 255 ? 1 : 0;
                                                    if (i7 != 0) {
                                                        i8 = i12;
                                                    } else if (!z) {
                                                        if (i12 >= 19 && new String(bArr, 0, 8, StandardCharsets.US_ASCII).equals("OpusHead") && (bArr[8] & UByte.MAX_VALUE) <= 15 && bArr[9] != 0) {
                                                            i8 = 0;
                                                            z = true;
                                                        }
                                                    } else if (i12 >= 16 && new String(bArr, 0, 8, StandardCharsets.US_ASCII).equals("OpusTags") && (i = (byteBufferOrder = ByteBuffer.wrap(bArr, 8, i12 - 8).order(ByteOrder.LITTLE_ENDIAN)).getInt()) >= 0 && i <= byteBufferOrder.remaining() - 4) {
                                                        String str2 = new String(bArr, byteBufferOrder.position(), i, StandardCharsets.UTF_8);
                                                        byteBufferOrder.position(byteBufferOrder.position() + i);
                                                        int i13 = byteBufferOrder.getInt();
                                                        if (i13 >= 0 && i13 <= byteBufferOrder.remaining() / 4) {
                                                            for (int i14 = 0; i14 < i13; i14++) {
                                                                if (byteBufferOrder.remaining() >= 4 && (i2 = byteBufferOrder.getInt()) >= 0 && i2 <= byteBufferOrder.remaining()) {
                                                                    byteBufferOrder.position(byteBufferOrder.position() + i2);
                                                                }
                                                            }
                                                            OpusFingerprint opusFingerprint2 = new OpusFingerprint(str2);
                                                            dataInputStream.close();
                                                            return opusFingerprint2;
                                                        }
                                                    }
                                                    i10++;
                                                    i3 = 0;
                                                }
                                            }
                                            try {
                                                int i15 = i3;
                                                if ((unsignedByte & 4) == 0) {
                                                    i3 = i15;
                                                }
                                            } catch (IOException unused) {
                                                return opusFingerprint;
                                            }
                                        }
                                    }
                                }
                            } else if (i9 == i5 && iReverseBytes == i6 && (unsignedByte & 2) == 0) {
                                if ((unsignedByte & 1) != i7) {
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            Throwable th2 = th;
                            try {
                                dataInputStream.close();
                                throw th2;
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                                throw th2;
                            }
                        }
                    } else {
                        opusFingerprint = null;
                    }
                    dataInputStream.close();
                    return opusFingerprint;
                }
                dataInputStream.close();
                return null;
            } catch (Throwable th4) {
                th = th4;
                opusFingerprint = null;
            }
        } catch (IOException unused2) {
            return null;
        }
    }
}
