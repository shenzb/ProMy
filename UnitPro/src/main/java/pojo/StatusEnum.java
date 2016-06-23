package pojo;

/**
 * Created by zhenbiao.shen on 2016/5/30.
 */
public enum  StatusEnum {

        INIT("初始状态", "INIT", 0),
        SUCCESS("成功", "SUCCESS", 1),
        FAIL("失败", "FAILED", 2),
        UNKNOWN("未知", "UNKNOWN", 3);

        public final String NAME;
        public final String CODE;
        public final int VALUE;

        private StatusEnum(String name, String code, int value) {
            this.NAME = name;
            this.CODE = code;
            this.VALUE = value;
        }

        public static StatusEnum fromCode(String code) {
            StatusEnum[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                StatusEnum status = arr$[i$];
                if(status.CODE.equals(code)) {
                    return status;
                }
            }

            return null;
        }

        public static StatusEnum fromValue(int value) {
            StatusEnum[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                StatusEnum status = arr$[i$];
                if(status.VALUE == value) {
                    return status;
                }
            }

            return null;
        }

}
