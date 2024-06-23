## To deploy [flashcards-service](https://github.com/navneetguptacse/flashcards-service) on K8s follow these steps,
First we will deploy the postgres db as a stateful set,

```
kubectl apply -f postgres
```

Deploy Flashcards-service as a deployment,

```
kubectl apply -f flashcards-service
```

To access swagger ui 
```
minikube service flashcards-service
```

## Cleanup

```
kubectl delete -f postgres
kubectl delete -f flashcards-service
```