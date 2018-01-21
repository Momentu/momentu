# Lambda Function

Create Thumbnail from uploaded Picture using AWS Lambda 

*assemble this project along with AWS Lambda dependencies into a jar file:*

```
mvn clean compile assembly:single
```

*log into aws and navigate to IAM page*

[AWS Identity and Access Management](https://console.aws.amazon.com/iam/home) 

*follow these steps to create role:*

1. click Create Role
2. select AWS service box
3. select Lambda
4. attach these Policies
    1. AmazonS3FullAccess
    2. AWSLambdaBasicExecutionRole
5. name Role 'lambda-s3-execution-role'
6. click Create Role to finalize

*navigate to AWS Lambda page*

[AWS Lambda](https://us-east-2.console.aws.amazon.com/lambda/home)

*click 'Create Function' and go through following steps*

1. choose 'Author from scratch box' 
2. name 'CreateThumbnail'
3. choose Java 8 as Runtime
4. for 'existing role' choose 'lambda-s3-execution-role'
5. click 'Create Function'
6. upload 'pic2thumbnail-1.0-SNAPSHOT-jar-with-dependencies.jar' from the target directory of this project
7. for 'Handler' enter 'momentu.S3EventProcessorCreateThumbnail::handleRequest'
8. under 'Add Triggers' click 'S3'
9. for bucket choose 'momentu'
10. under 'event type' select 'PUT'
11. make sure 'Enable Trigger' is checked
12. click 'Save' to store the new Lambda Function

*S3EventProcessorCreateThumbnail::handleRequest will now run whenever a new image is uploaded to the momentu bucket*

*RequestHandler method of this project can be found at:*

[Step 2.1: Create a Deployment Package](https://docs.aws.amazon.com/lambda/latest/dg/with-s3-example-deployment-pkg.html)

