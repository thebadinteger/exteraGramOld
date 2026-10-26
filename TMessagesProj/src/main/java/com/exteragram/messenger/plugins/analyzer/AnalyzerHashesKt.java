package com.exteragram.messenger.plugins.analyzer;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"HEX", _UrlKt.FRAGMENT_ENCODE_SET, "toHex", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "sha256Hex", "analyzerBuildId", "context", "Landroid/content/Context;", "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAnalyzerHashes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnalyzerHashes.kt\ncom/exteragram/messenger/plugins/analyzer/AnalyzerHashesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,37:1\n1#2:38\n*E\n"})
public final class AnalyzerHashesKt {
    private static final char[] HEX = "0123456789abcdef".toCharArray();

    public static final String toHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            byte b2 = bArr[i];
            int i2 = b2 & UByte.MAX_VALUE;
            int i3 = i * 2;
            char[] cArr2 = HEX;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public static final String sha256Hex(byte[] bArr) {
        return toHex(MessageDigest.getInstance("SHA-256").digest(bArr));
    }

    public static final String analyzerBuildId(Context context) {
        try {
            InputStream inputStreamOpen = context.getAssets().open("plugin_analyzer/build_id");
            try {
                byte[] bytes = ByteStreamsKt.readBytes(inputStreamOpen);
                CloseableKt.closeFinally(inputStreamOpen, null);
                String string = StringsKt.trim((CharSequence) new String(bytes, Charsets.UTF_8)).toString();
                if (string.length() == 0) {
                    return null;
                }
                return string;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(inputStreamOpen, th);
                    throw th2;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
