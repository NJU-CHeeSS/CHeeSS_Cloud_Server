package edu.nju.cheess.cloudserver.util;

public enum Diploma {

    JUNIOR              ("初中及以下"),
    SECONDARY_TECHNICAL ("中技"),
    SECONDARY           ("中专"),
    COLLEGE             ("大专"),
    UNDERGRADUATE       ("本科"),
    MASTER              ("硕士"),
    DOCTOR              ("博士"),
    ;

    public String diploma;

    Diploma(String diploma) {
        this.diploma = diploma;
    }

    /**
     * 字符串转枚举类型
     */
    public static Diploma stringToDiploma(String string) {
        for (Diploma type : Diploma.values()) {
            if (string.equals(type.diploma)) {
                return type;
            }
        }
        return null;
    }

}
