package top.simba1949.request;

import java.io.Serializable;

/**
 * @author anthony
 * @version 2023/9/6
 */
public class KeywordRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "KeywordRequest{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
