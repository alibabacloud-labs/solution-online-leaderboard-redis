# solution-online-leaderboard-redis

### Project URL
[https://github.com/alibabacloud-labs/solution-online-leaderboard-redis](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis)

### Architecture Overview
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/archi.png)

### Deployment
#### Terraform
Use terraform to provision ECS and Redis instances that used in this solution against this .tf file:
[https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/blob/main/deployment/terraform/main.tf](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/blob/main/deployment/terraform/main.tf)


For more information about how to use Terraform, please refer to this tutorial: [https://www.youtube.com/watch?v=zDDFQ9C9XP8](https://www.youtube.com/watch?v=zDDFQ9C9XP8)


### Run Demo
#### Step 1: Set Redis access password
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step1-1.png)
Copy and remember this access endpoint URL, which will be used to replace in the Java application source code for accessing the Redis instance.
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step1-2.png)
Reset the password here, which will be used to replace in the Java application source code for accessing the Redis instance.


#### Step 2: Log on ECS & setup environment
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step2-1.png)
Reset the ECS password here and restart the instance to apply the change.


![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step2-2.png)
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step2-3.png)
Click “Connect” button to log on ECS via VNC.
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step2-4.png)
You need to modify VNC password for the 1st time for logging on the ECS.
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step2-5.png)
Click "Enter Copy Commands" to execute the following commands one by one, which are to keep the apt update, install Java and Maven for building and running the demo application later.
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step2-6.png)
```bash
sudo apt-get update
sudo apt-get install openjdk-8-jdk
apt install maven
```






#### Step 3: Modify & run application
Click "Enter Copy Commands" to execute the following commands one by one, which are to download the demo application code.
```bash
wget https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/source.tar.gz
tar xvf source.tar.gz && cd source
```


Vim to modify the Java code to replace the Redis access endpoint URL and password accordingly in the Step 1.
```bash
vim src/main/java/test/GameRankSample.java
```
```bash
mvn clean package assembly:single -DskipTests
java -classpath target/demo-0.0.1-SNAPSHOT.jar test.GameRankSample
```
Running result:
![image.png](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/raw/main/images/step3-1.png)
