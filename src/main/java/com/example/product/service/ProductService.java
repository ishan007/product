package com.example.product.service;


import com.example.order.OrderRequest;
import com.example.order.OrderResponse;
import com.example.product.ProductRequest;
import com.example.product.ProductResponse;
import com.example.product.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

    private final OrderClient client = new OrderClient();

    @Override
    public void placeOrder(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        client.placeOrder(OrderRequest.newBuilder().setProduct(request.getProduct()).build(), new StreamObserver<>() {
            @Override
            public void onNext(OrderResponse value) {
                ProductResponse response = ProductResponse.newBuilder().addProducts(value.getProducts(0)).build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        });
    }

    @Override
    public void updateOrder(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        client.updateOrder(OrderRequest.newBuilder().setProduct(request.getProduct()).build(), new StreamObserver<>() {
            @Override
            public void onNext(OrderResponse value) {
                ProductResponse response = ProductResponse.newBuilder().addAllProducts(value.getProductsList()).build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        });
    }
}
