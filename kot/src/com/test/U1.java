//package com.test;
//
//import kotlin.Metadata;
//
//public final class U1 {
//    public static final U1.Companion Companion = new U1.Companion((DefaultConstructorMarker)null);
//
//    private U1() {
//    }
//
//    @Metadata(
//            mv = {1, 1, 16},
//            bv = {1, 0, 3},
//            k = 1,
//            d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"},
//            d2 = {"Lcom/test/U1$Companion;", "", "()V", "getScore", "", "value", "kot"}
//    )
//    public static final class Companion {
//        public final int getScore(int value) {
//            return 2 * value;
//        }
//
//        private Companion() {
//        }
//
//        // $FF: synthetic method
//        public Companion(DefaultConstructorMarker $constructor_marker) {
//            this();
//        }
//    }
//}
//
//public final class U2 {
//    public static final U2 INSTANCE;
//
//    public final int getScore(int value) {
//        return 2 * value;
//    }
//
//    private U2() {
//    }
//
//    static {
//        U2 var0 = new U2();
//        INSTANCE = var0;
//    }
//}
//
//public final class U1Kt {
//    public static final void x() {
//        U1.Companion.getScore(1);
//        U2.INSTANCE.getScore(1);
//    }
//}