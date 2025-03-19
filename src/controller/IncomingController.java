package controller;

public interface IncomingController {
    void requestIncoming(String userId);
    void approveIncoming(String adminId);
}
