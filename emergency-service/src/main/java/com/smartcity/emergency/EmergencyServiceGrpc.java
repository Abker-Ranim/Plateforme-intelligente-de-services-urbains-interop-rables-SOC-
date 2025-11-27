package com.smartcity.emergency;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: emergency.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EmergencyServiceGrpc {

  private EmergencyServiceGrpc() {}

  public static final String SERVICE_NAME = "EmergencyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.smartcity.emergency.AlertRequest,
      com.smartcity.emergency.AlertResponse> getSendAlertMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendAlert",
      requestType = com.smartcity.emergency.AlertRequest.class,
      responseType = com.smartcity.emergency.AlertResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smartcity.emergency.AlertRequest,
      com.smartcity.emergency.AlertResponse> getSendAlertMethod() {
    io.grpc.MethodDescriptor<com.smartcity.emergency.AlertRequest, com.smartcity.emergency.AlertResponse> getSendAlertMethod;
    if ((getSendAlertMethod = EmergencyServiceGrpc.getSendAlertMethod) == null) {
      synchronized (EmergencyServiceGrpc.class) {
        if ((getSendAlertMethod = EmergencyServiceGrpc.getSendAlertMethod) == null) {
          EmergencyServiceGrpc.getSendAlertMethod = getSendAlertMethod =
              io.grpc.MethodDescriptor.<com.smartcity.emergency.AlertRequest, com.smartcity.emergency.AlertResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendAlert"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smartcity.emergency.AlertRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smartcity.emergency.AlertResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmergencyServiceMethodDescriptorSupplier("SendAlert"))
              .build();
        }
      }
    }
    return getSendAlertMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.smartcity.emergency.Empty,
      com.smartcity.emergency.ActiveAlertsResponse> getGetActiveAlertsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetActiveAlerts",
      requestType = com.smartcity.emergency.Empty.class,
      responseType = com.smartcity.emergency.ActiveAlertsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.smartcity.emergency.Empty,
      com.smartcity.emergency.ActiveAlertsResponse> getGetActiveAlertsMethod() {
    io.grpc.MethodDescriptor<com.smartcity.emergency.Empty, com.smartcity.emergency.ActiveAlertsResponse> getGetActiveAlertsMethod;
    if ((getGetActiveAlertsMethod = EmergencyServiceGrpc.getGetActiveAlertsMethod) == null) {
      synchronized (EmergencyServiceGrpc.class) {
        if ((getGetActiveAlertsMethod = EmergencyServiceGrpc.getGetActiveAlertsMethod) == null) {
          EmergencyServiceGrpc.getGetActiveAlertsMethod = getGetActiveAlertsMethod =
              io.grpc.MethodDescriptor.<com.smartcity.emergency.Empty, com.smartcity.emergency.ActiveAlertsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetActiveAlerts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smartcity.emergency.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.smartcity.emergency.ActiveAlertsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EmergencyServiceMethodDescriptorSupplier("GetActiveAlerts"))
              .build();
        }
      }
    }
    return getGetActiveAlertsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmergencyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmergencyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmergencyServiceStub>() {
        @java.lang.Override
        public EmergencyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmergencyServiceStub(channel, callOptions);
        }
      };
    return EmergencyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmergencyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmergencyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmergencyServiceBlockingStub>() {
        @java.lang.Override
        public EmergencyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmergencyServiceBlockingStub(channel, callOptions);
        }
      };
    return EmergencyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmergencyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EmergencyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EmergencyServiceFutureStub>() {
        @java.lang.Override
        public EmergencyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EmergencyServiceFutureStub(channel, callOptions);
        }
      };
    return EmergencyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class EmergencyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendAlert(com.smartcity.emergency.AlertRequest request,
        io.grpc.stub.StreamObserver<com.smartcity.emergency.AlertResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendAlertMethod(), responseObserver);
    }

    /**
     */
    public void getActiveAlerts(com.smartcity.emergency.Empty request,
        io.grpc.stub.StreamObserver<com.smartcity.emergency.ActiveAlertsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetActiveAlertsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendAlertMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.smartcity.emergency.AlertRequest,
                com.smartcity.emergency.AlertResponse>(
                  this, METHODID_SEND_ALERT)))
          .addMethod(
            getGetActiveAlertsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.smartcity.emergency.Empty,
                com.smartcity.emergency.ActiveAlertsResponse>(
                  this, METHODID_GET_ACTIVE_ALERTS)))
          .build();
    }
  }

  /**
   */
  public static final class EmergencyServiceStub extends io.grpc.stub.AbstractAsyncStub<EmergencyServiceStub> {
    private EmergencyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmergencyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmergencyServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendAlert(com.smartcity.emergency.AlertRequest request,
        io.grpc.stub.StreamObserver<com.smartcity.emergency.AlertResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendAlertMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getActiveAlerts(com.smartcity.emergency.Empty request,
        io.grpc.stub.StreamObserver<com.smartcity.emergency.ActiveAlertsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetActiveAlertsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EmergencyServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<EmergencyServiceBlockingStub> {
    private EmergencyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmergencyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmergencyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.smartcity.emergency.AlertResponse sendAlert(com.smartcity.emergency.AlertRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendAlertMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.smartcity.emergency.ActiveAlertsResponse getActiveAlerts(com.smartcity.emergency.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetActiveAlertsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EmergencyServiceFutureStub extends io.grpc.stub.AbstractFutureStub<EmergencyServiceFutureStub> {
    private EmergencyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EmergencyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EmergencyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smartcity.emergency.AlertResponse> sendAlert(
        com.smartcity.emergency.AlertRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendAlertMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.smartcity.emergency.ActiveAlertsResponse> getActiveAlerts(
        com.smartcity.emergency.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetActiveAlertsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_ALERT = 0;
  private static final int METHODID_GET_ACTIVE_ALERTS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EmergencyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EmergencyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_ALERT:
          serviceImpl.sendAlert((com.smartcity.emergency.AlertRequest) request,
              (io.grpc.stub.StreamObserver<com.smartcity.emergency.AlertResponse>) responseObserver);
          break;
        case METHODID_GET_ACTIVE_ALERTS:
          serviceImpl.getActiveAlerts((com.smartcity.emergency.Empty) request,
              (io.grpc.stub.StreamObserver<com.smartcity.emergency.ActiveAlertsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EmergencyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EmergencyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.smartcity.emergency.EmergencyProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EmergencyService");
    }
  }

  private static final class EmergencyServiceFileDescriptorSupplier
      extends EmergencyServiceBaseDescriptorSupplier {
    EmergencyServiceFileDescriptorSupplier() {}
  }

  private static final class EmergencyServiceMethodDescriptorSupplier
      extends EmergencyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EmergencyServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EmergencyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmergencyServiceFileDescriptorSupplier())
              .addMethod(getSendAlertMethod())
              .addMethod(getGetActiveAlertsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
