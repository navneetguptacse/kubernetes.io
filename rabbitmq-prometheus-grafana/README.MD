# Introduction
We are going to deploy a rabbitmq monitoring stack comprising of Rabbitmq cluster deployed using rabbmitmq K8s operator, Prometheus cluster deployed using prometheus K8s operator and Grafana for visualization of the metrics.

## Start minikube using command

By default it is assumed, that the kubelet uses token authentication and authorization, as otherwise Prometheus needs a client certificate, which gives it full access to the kubelet, rather than just the metrics. Token authentication and authorization allows more fine grained and easier access control.

So we need to enable these falgs upon startup,

`--authentication-token-webhook=true`

`--authorization-mode=Webhook`

```
minikube delete && minikube start --kubernetes-version=v1.20.0 --bootstrapper=kubeadm --extra-config=kubelet.authentication-token-webhook=true --extra-config=kubelet.authorization-mode=Webhook --extra-config=scheduler.address=0.0.0.0 --extra-config=controller-manager.address=0.0.0.0
```

## Change current working directory to rabbitmq-prometheus-grafana

```
cd  rabbitmq-prometheus-grafana
```

## Create a new namespace and set current context

```
kubectl create ns rabbit-monitoring
kubectl config set-context --current --namespace=rabbit-monitoring
```

## Install the Rabbitmq K8s operator and deploy a Rabbitmq cluster

```
kubectl apply -f https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml
kubectl apply -f rabbitmq/
```

## Install the Prometheus K8s operator and deploy a Service Monitor, Prometheus instances.

First we need to clone the kube-prometheus from the >>> [URL](https://github.com/prometheus-operator/kube-prometheus#customizing-kube-prometheus)

Install Prometheus operator

```
cd kube-prometheus
kubectl apply -f manifests/setup
```

Check if Prometheus operator is installed correctly

```
kubectl get customresourcedefinitions.apiextensions.k8s.io servicemonitors.monitoring.coreos.com

NAME                                    CREATED AT
servicemonitors.monitoring.coreos.com   2021-10-22T18:19:12Z
```

Now follow the below steps to create a Service Monitor to scrape rabbitmq and connect it with the Prometheus instance

```
kubectl apply -f prometheus
```

Verify that Prometheus is able to get metrics from Rabbitmq cluster.

```
kubectl port-forward service/prometheus-operated 9090
```

Navigate to URL and verfiy [http://localhost:9090/targets](http://localhost:9090/targets)

## Install Grafana

```
kubectl apply -f grafana/
kubectl port-forward service/grafana 3000
```

Access Grafana dashboard using [http://localhost:3000/](http://localhost:3000/)

Configure a Prometheus data source and use url [http://prometheus:9090](http://prometheus:9090) with default settings.

Import a RabbitMQ overview dashboard using the json file provided or from dashboard ID "10991".

## Cleanup

```
kubectl delete -f grafana
kubectl delete -f prometheus
kubectl delete -f rabbitmq
kubectl delete -f https://github.com/rabbitmq/cluster-operator/releases/latest/download/cluster-operator.yml
```
