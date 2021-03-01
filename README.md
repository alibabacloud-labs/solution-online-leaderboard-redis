# solution-online-leaderboard-redis

### Project URL
[https://github.com/alibabacloud-labs/solution-online-leaderboard-redis](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis)

### Architecture Overview
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614587375187-45c11995-15e2-42c6-8e4b-651f8e433813.png#align=left&display=inline&height=682&margin=%5Bobject%20Object%5D&name=image.png&originHeight=682&originWidth=1275&size=264455&status=done&style=none&width=1275)

### Deployment
#### Terraform
Use terraform to provision ECS and Redis instances that used in this solution against this .tf file:
[https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/blob/main/deployment/terraform/main.tf](https://github.com/alibabacloud-labs/solution-online-leaderboard-redis/blob/main/deployment/terraform/main.tf)


For more information about how to use Terraform, please refer to this tutorial: [https://www.youtube.com/watch?v=zDDFQ9C9XP8](https://www.youtube.com/watch?v=zDDFQ9C9XP8)


### Run Demo
#### Step 1: Set Redis access password
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614587375187-45c11995-15e2-42c6-8e4b-651f8e433813.png#align=left&display=inline&height=682&margin=%5Bobject%20Object%5D&name=image.png&originHeight=682&originWidth=1275&size=264455&status=done&style=none&width=1275)
Copy and remember this access endpoint URL, which will be used to replace in the Java application source code for accessing the Redis instance.
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614587502795-e5570d14-1f92-4ebe-a44d-9f6b3f377d31.png#align=left&display=inline&height=368&margin=%5Bobject%20Object%5D&name=image.png&originHeight=368&originWidth=1299&size=149230&status=done&style=none&width=1299)
Reset the password here, which will be used to replace in the Java application source code for accessing the Redis instance.


#### Step 2: Log on ECS & setup environment
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614589525507-61c19a9c-1456-4d77-8c29-8cc635ccf693.png#align=left&display=inline&height=616&margin=%5Bobject%20Object%5D&name=image.png&originHeight=616&originWidth=1288&size=127500&status=done&style=none&width=1288)
Reset the ECS password here and restart the instance to apply the change.


![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614589615475-34d74563-beb2-47f4-8731-2493292c8f0a.png#align=left&display=inline&height=369&margin=%5Bobject%20Object%5D&name=image.png&originHeight=369&originWidth=957&size=49149&status=done&style=none&width=957)
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614589655087-42f42ede-9529-46e6-a22c-a98bc6594219.png#align=left&display=inline&height=417&margin=%5Bobject%20Object%5D&name=image.png&originHeight=417&originWidth=645&size=41619&status=done&style=none&width=645)
Click “Connect” button to log on ECS via VNC.
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614589704900-57f20754-057a-490a-8216-93a67b568e0d.png#align=left&display=inline&height=309&margin=%5Bobject%20Object%5D&name=image.png&originHeight=309&originWidth=599&size=21690&status=done&style=none&width=599)
You need to modify VNC password for the 1st time for logging on the ECS.
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614589793620-c9fe3567-24a6-45a4-8991-e22930d799b7.png#align=left&display=inline&height=344&margin=%5Bobject%20Object%5D&name=image.png&originHeight=344&originWidth=811&size=31635&status=done&style=none&width=811)
Click "Enter Copy Commands" to execute the following commands one by one, which are to keep the apt update, install Java and Maven for building and running the demo application later.
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614589881552-c1b88b7d-c419-43fb-8aa1-56814fdde8df.png#align=left&display=inline&height=228&margin=%5Bobject%20Object%5D&name=image.png&originHeight=228&originWidth=936&size=36896&status=done&style=none&width=936)
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
![image.png](https://intranetproxy.alipay.com/skylark/lark/0/2021/png/32590/1614591573376-94005a7b-8f6c-406e-a18c-4124ddbff2ce.png#align=left&display=inline&height=814&margin=%5Bobject%20Object%5D&name=image.png&originHeight=814&originWidth=714&size=230960&status=done&style=none&width=714)
