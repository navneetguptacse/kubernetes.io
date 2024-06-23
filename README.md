# Kubernetes
A project to explore K8s concepts using minikube.

## Setups
1. [Monogo cluster](https://github.com/navneetguptacse/kubernetes.io/tree/master/mongo)
2. [RabbitMQ cluster](https://github.com/navneetguptacse/kubernetes.io/tree/master/rabbitmq)
3. [RabbitMQ Prometheus Grafana](https://github.com/navneetguptacse/kubernetes.io/tree/master/rabbitmq-prometheus-grafana)
4. [Expense-tracker](https://github.com/navneetguptacse/kubernetes.io/tree/master/expense-tracker)

## `kubectl` commands
#### Create deployment: 

```kubectl create deployment [name]```

#### Edit deployment: 

```kubectl edit deployment [name]```

#### Delete deployment: 

```kubectl delete deployment [name]```

#### Status of different K8s components:

```
kubectl get nodes
kubectl get pod
kubectl get pod -o wide
kubectl get services
kubectl get replicaset
kubectl get deployment
kubectl get secret
```

#### Debugging pods:

```
kubectl logs [podname]
kubectl exec -ti [podname] -- bin/bash
kubectl describe pod [podname]
kubectl get deployment [deployment name] -o yaml > filename.yaml
```

#### Using config file:

```
kubectl apply -f [filename]
kubectl delete -f [filename]
```

#### namespaces:
```kubectl create namespace [name]```

#### Check service:
```minikube service servicename```

#### Other commands:
```kubectl cluster-info```
