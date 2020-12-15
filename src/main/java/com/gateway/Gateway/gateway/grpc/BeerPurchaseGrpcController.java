package com.gateway.Gateway.gateway.grpc;



import com.beer_purchase.Beer_Purchase_Service.*;
import com.gateway.Gateway.gateway.dto.SoldBeer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class BeerPurchaseGrpcController extends BeerPurchaseServiceGrpc.BeerPurchaseServiceImplBase {

    private String url = "pub-soldbeer";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9085).usePlaintext().build();;
    private BeerPurchaseServiceGrpc.BeerPurchaseServiceBlockingStub stub = BeerPurchaseServiceGrpc.newBlockingStub(channel);


    @Override
    public void all(AllSoldBeerRequest request, StreamObserver<AllSoldBeerResponse> responseObserver) {
        AllSoldBeerRequest allSoldBeerRequest = AllSoldBeerRequest.newBuilder().build();
        AllSoldBeerResponse response = stub.all(allSoldBeerRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(SoldBeerRequest request, StreamObserver<SoldBeerRequest> responseObserver) {
        SoldBeerRequest soldBeer = SoldBeerRequest.newBuilder().
                setSoldBeerId(request.getSoldBeerId()).
                setMoneyGain(request.getMoneyGain()).
                setBeerType(request.getBeerType()).
                setVisitorsId(request.getVisitorsId()).
                build();
        SoldBeerRequest response = stub.add(soldBeer);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



    @Override
    public void byId(SoldBeerRequestId request, StreamObserver<SoldBeerRequest> responseObserver) {
        SoldBeerRequestId soldBeerRequest = SoldBeerRequestId.newBuilder().
                setId(request.getId()).
                build();
        SoldBeerRequest response = stub.byId(soldBeerRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



    @Override
    public void delete(DeleteSoldBeerRequest request, StreamObserver<DeleteSoldBeerResponse> responseObserver) {
        DeleteSoldBeerRequest soldBeerRequest = DeleteSoldBeerRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteSoldBeerResponse response = stub.delete(soldBeerRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
