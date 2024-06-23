## To deploy [`expense-tracker`](https://github.com/navneetguptacse/expense-tracker-spring) on K8s follow these steps,
First we will deploy the postgres db as a stateful set,

```
kubectl apply -f postgres
```

Deploy expense tracker as a deployment,

```
kubectl apply -f expense-tracker
```

To access swagger UI 
```
minikube service expense-tracker-service
```

## Clean-up

```
kubectl delete -f postgres
kubectl delete -f expense-tracker
```