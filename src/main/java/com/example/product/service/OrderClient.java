package com.example.product.service;

import com.example.order.OrderRequest;
import com.example.order.OrderResponse;
import com.example.order.OrderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class OrderClient {

    private final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
    private final OrderServiceGrpc.OrderServiceStub stub = OrderServiceGrpc.newStub(channel);

    public void placeOrder(OrderRequest request, StreamObserver<OrderResponse> observer){
        System.out.println("Placing order in stub");
        stub.placeOrder(OrderRequest.newBuilder().setProduct(request.getProduct()).build(), observer);
    }

    public  void updateOrder(OrderRequest request, StreamObserver<OrderResponse> observer){
        stub.updateOrder(request, observer);
    }
}
