package com.gateway.Gateway.gateway.grpc;



import com.gateway.Gateway.gateway.dto.Saloon;
import com.saloon.Saloon.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@GRpcService
public class SaloonGrpcController extends SaloonServiceGrpc.SaloonServiceImplBase {


    private String url = "pub-saloon";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9084).usePlaintext().build();;
    private SaloonServiceGrpc.SaloonServiceBlockingStub stub = SaloonServiceGrpc.newBlockingStub(channel);


    @Override
    public void all(AllSaloonRequest request, StreamObserver<AllSaloonResponse> responseObserver) {
        AllSaloonRequest allSaloonRequest = AllSaloonRequest.newBuilder().build();
        AllSaloonResponse response = stub.all(allSaloonRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(SaloonResponse request, StreamObserver<SaloonResponse> responseObserver) {
        SaloonResponse saloon = SaloonResponse.newBuilder().
                setTableId(request.getTableId()).
                setPlaceNum(request.getPlaceNum()).
                setUniqueName(request.getUniqueName()).
                setOccupiedTableId(request.getOccupiedTableId()).
                setFreeTableId(request.getFreeTableId()).
                build();
        SaloonResponse response = stub.add(saloon);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void byId(SaloonRequestId request, StreamObserver<SaloonResponse> responseObserver) {
        SaloonRequestId saloonRequest = SaloonRequestId.newBuilder().
                setId(request.getId()).
                build();
        SaloonResponse response = stub.byId(saloonRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void byNum(VisitorsRequest request, StreamObserver<SaloonResponse> responseObserver) {
        VisitorsRequest saloonRequest = VisitorsRequest.newBuilder().
                setVisitorId(request.getVisitorId()).
                setVisitorsNum(request.getVisitorsNum()).
                setOccupiedTableID(request.getOccupiedTableID()).
                build();
        SaloonResponse response = stub.byNum(saloonRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void setFree(FreeTableRequest request, StreamObserver<FreeTableRequest> responseObserver) {
        FreeTableRequest freeTableRequest = FreeTableRequest.newBuilder().
                setFreeTableId(request.getFreeTableId()).
                setSaloonId(request.getSaloonId()).
                build();
        FreeTableRequest response = stub.setFree(freeTableRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(DeleteSaloonRequest request, StreamObserver<DeleteSaloonResponse> responseObserver) {
        DeleteSaloonRequest saloonRequest = DeleteSaloonRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteSaloonResponse response = stub.delete(saloonRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
