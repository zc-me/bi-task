package com.timing.bitaskcore.util;//package com.xxl.job.core.util;
//
///**
// * sharding vo
// */
//public class ShardingUtil {
//
//    private static InheritableThreadLocal<ShardingVO> contextHolder = new InheritableThreadLocal<ShardingVO>();
//
//    public static class ShardingVO {
//
//        private int index;  // sharding index
//        private int total;  // sharding total
//
//        public ShardingVO(int index, int total) {
//            this.index = index;
//            this.total = total;
//        }
//
//        public int getIndex() {
//            return index;
//        }
//
//        public void setIndex(int index) {
//            this.index = index;
//        }
//
//        public int getTotal() {
//            return total;
//        }
//
//        public void setTotal(int total) {
//            this.total = total;
//        }
//    }
//
//    public static void setShardingVo(ShardingVO shardingVo){
//        contextHolder.set(shardingVo);
//    }
//
//    public static ShardingVO getShardingVo(){
//        return contextHolder.get();
//    }
//
//}
