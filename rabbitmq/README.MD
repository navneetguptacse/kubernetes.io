## Introduction
Kubernetes Operators are software extensions to Kubernetes that provide custom resources for management of applications, services and their components.
<li>RabbitMQ Cluster Kubernetes Operator automates provisioning, management, and operations of RabbitMQ clusters running on Kubernetes.
<li>RabbitMQ Cluster Kubernetes Operator provides a consistent and easy way to deploy RabbitMQ clusters to Kubernetes and run them, including "day two" (continuous) operations. 
<li>RabbitMQ clusters deployed using the Operator can be used by applications running on Kubernetes or outside of Kubernetes.

## Setup 
<li> Download and install rabbitmq K8s cluster operator

```
kubectl apply -f https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml
```

<li> Check the rabbitmq namespace 

```
kubectl get all -o wide -n rabbitmq-system
```

<li> Confirm Service Availability

```
kubectl get customresourcedefinitions.apiextensions.k8s.io
```

<li> Create a rabbitmq cluster. It will import custom definitions upon node boot. It is configured with vHosts and a user.

```
kubectl apply -f rabbitmq.yaml
```

This will deploy in current namespace (default)

<li> Check rabbitmq-cluster cluster

```
kubectl get all
NAME                            READY   STATUS            RESTARTS   AGE
pod/rabbitmq-cluster-server-0   0/1     PodInitializing   0          5s      

NAME                             TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                        AGE
service/kubernetes               ClusterIP   10.96.0.1       <none>        443/TCP                        13d
service/rabbitmq-cluster         ClusterIP   10.111.41.201   <none>        5672/TCP,15672/TCP,15692/TCP   5s
service/rabbitmq-cluster-nodes   ClusterIP   None            <none>        4369/TCP,25672/TCP             5s

NAME                                       READY   AGE
statefulset.apps/rabbitmq-cluster-server   0/1     5s

NAME                                            ALLREPLICASREADY   RECONCILESUCCESS   AGE
rabbitmqcluster.rabbitmq.com/rabbitmq-cluster   False              Unknown            5s
```

<li> To access rabbitmq management

```
kubectl port-forward "service/rabbitmq-cluster" 15672
```

Management URL: localhost:15672

Some commands

```
Get username: kubectl get secret rabbitmq-cluster-default-user -o jsonpath='{.data.username}'
Get password: kubectl get secret rabbitmq-cluster-default-user -o jsonpath='{.data.password}'
Get clusterIp: kubectl get service rabbitmq-cluster -o jsonpath='{.spec.clusterIP}'
```

<li> To test the broker

```
kubectl run perf-test --image=pivotalrabbitmq/perf-test -- --uri amqp://uname:pwd@clusterIp
```

<li> Cleanup

```
kubectl delete -f rabbitmq.yml
kubectl delete -f configmap.yml
kubectl delete -f https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml
```

## Test client
Login to RabbitMQ management portal and create a new user called "guest".

<li> Set the following in appplication.properties

```
spring.profiles.active=usage
test.client.duration=10000
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```
  
<li> Build the jar using
  
```
  mvn clean package
```
   
<li> To run sender, run
    
  ```
  java -jar rabbitmq-client-0.0.1-SNAPSHOT.jar --spring.profiles.active=test,sender
  ```
    
<li> Open another terminal and run reciever
    
  ```
    java -jar rabbitmq-client-0.0.1-SNAPSHOT.jar --spring.profiles.active=test,reciever
  ```
    
  
## References
This guide was created using [RabbitMQ tutorials](https://www.rabbitmq.com/kubernetes/operator/operator-overview.html)
  
