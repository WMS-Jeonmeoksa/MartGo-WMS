package controller;

import javax.management.relation.Role;

public interface OutgoingController {
    void requestOutgoing(String userId);
    void approveOutgoing(String adminId);
}
