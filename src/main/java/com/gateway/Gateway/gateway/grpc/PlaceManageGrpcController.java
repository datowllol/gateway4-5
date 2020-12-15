package com.gateway.Gateway.gateway.grpc;

import com.gateway.Gateway.gateway.dto.Visitors;
import com.placeManage.Place_Manage_Service.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class PlaceManageGrpcController extends PlaceManageServiceGrpc.PlaceManageServiceImplBase {


    private String url = "pub-visitors";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 9083).usePlaintext().build();

    private PlaceManageServiceGrpc.PlaceManageServiceBlockingStub stub = PlaceManageServiceGrpc.newBlockingStub(channel);


    @Override
    public void all(AllVisitorsRequest request, StreamObserver<AllVisitorsResponse> responseObserver) {
        AllVisitorsRequest allVisitorsRequest = AllVisitorsRequest.newBuilder().build();
        AllVisitorsResponse response = stub.all(allVisitorsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(VisitorsRequest request, StreamObserver<VisitorsRequest> responseObserver) {
        VisitorsRequest visitors = VisitorsRequest.newBuilder().
                setVisitorId( request.getVisitorId()) .
                setVisitorsNum(request.getVisitorsNum()).
                setOccupiedTableID(request.getOccupiedTableID()).
                build();
        VisitorsRequest response = stub.add(visitors);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void byId(VisitorsRequestId request, StreamObserver<VisitorsRequest> responseObserver) {
        VisitorsRequestId visitorsRequest = VisitorsRequestId.newBuilder().
                setId(request.getId()).
                build();
        VisitorsRequest response = stub.byId(visitorsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    @Override
    public void delete(DeleteVisitorsRequest request, StreamObserver<DeleteVisitorsResponse> responseObserver) {
        DeleteVisitorsRequest visitorsRequest = DeleteVisitorsRequest.newBuilder().
                setId(request.getId()).
                build();
        DeleteVisitorsResponse response = stub.delete(visitorsRequest);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}