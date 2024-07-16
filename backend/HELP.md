# Help
## How to use Docker 🐳
#### 1. Build the image docker from que latest application version.
```bash
git pull
```
#### 2. Build a new docker image.

Need to run at the same path of the dockerfile

```bash
cd code/api/
```
```bash
docker build --build-arg DB_ENDPOINT=db-endpoint --build-arg DB_USERNAME=db-user --build-arg DB_PASSWORD=db-password --build-arg DB_NAME=db-name --build-arg JWT_SECRET=jwt-secret -t finance-management-application .
```

> ALERT to the password with special caracters. Use '' if is necessary.

#### 3. Run a new container from the image created.

*(Optional)* You can check for your image running `docker images`

```bash
docker run -e DB_ENDPOINT=db-endpoint -e DB_USERNAME=db-user -e DB_PASSWORD=db-password -e DB_NAME=db-name -e JWT_SECRET=jwt-secret -p 8080:8080 -d finance-management-application
```
