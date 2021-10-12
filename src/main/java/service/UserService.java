package service;

import generated.*;
import io.grpc.stub.StreamObserver;

public class UserService extends UserGrpc.UserImplBase {

    public void createUser(UserRequest request, StreamObserver<APIResponse> responseStreamObserver) {
        String username = request.getUsername();
        String password = request.getPassword();

        APIResponse.Builder response = APIResponse.newBuilder();
        if (username.isEmpty() || password.isEmpty()) {
            response.setResponseCode(400).setResponseMessage("Failure to create a User");
        } else {
            response.setResponseCode(200).setResponseMessage("Success");
        }
        responseStreamObserver.onNext(response.build());
        responseStreamObserver.onCompleted();
    }

    public void getUsers(Empty request, StreamObserver<UserResponse> responseStreamObserver) {
        try {
            for (int i = 1; i <= 10; i++) {
                UserResponse response = UserResponse.newBuilder().setUsername("John Wick " + i).setPassword("").build();
                responseStreamObserver.onNext(response);

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            responseStreamObserver.onCompleted();
        }
    }
}
