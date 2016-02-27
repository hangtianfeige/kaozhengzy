package utils;

/**
 * 作者：刘帅 on 2016/2/27 13:58
 * 邮箱：857279611@qq.com
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
