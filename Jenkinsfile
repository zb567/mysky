pipeline {
    agent any

    environment {
            REMOTE_SERVER = 'root@10.101.169.172' // 替换为你的远程用户名和IP或域名
            PROJECT_PATH = '/var/jenkins_home/workspace/testpipline'
        }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                // 在这里添加构建步骤，例如编译代码
                sh '/var/jenkins_home/file/apache-maven-3.8.8/bin/mvn clean package'
            }
        }
//         stage('Test') {
//             steps {
//                 echo 'Testing..'
//                 // 在这里添加测试步骤，例如运行单元测试
//                 sh 'mvn test'
//             }
//         }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                // 在这里添加部署步骤，例如将构建好的应用部署到服务器
//                 sh 'scp target/myapp.jar user@server:/opt/deploy/'

                // 上传文件到服务器
//                 withCredentials([sshUserPrivateKey(credentials
//                 Id: 'jenkins-ssh-key', keyFileVariable: 'SSH_KEY')]) {
//                     sh 'scp -i $SSH_KEY target/myapp.jar user@server:/opt/deploy/'
//                 }


                sh "ssh ${REMOTE_SERVER} 'rm -rf /root/myjenkins/mysky/*'"
                // 上传文件到服务器
                sh "scp ${PROJECT_PATH}/mysky-admin/target/mysky-admin.jar ${REMOTE_SERVER}:~/myjenkins/mysky"
                sh "scp ${PROJECT_PATH}/docker-compose.yml ${REMOTE_SERVER}:~/myjenkins/mysky"
                sh "scp ${PROJECT_PATH}/jenkins.sh ${REMOTE_SERVER}:~/myjenkins/mysky"
//                 // 执行远程服务器脚本
//                 sshagent(['your-ssh-credentials-id']) {
//                     sh 'ssh user@server "/opt/deploy/deploy-script.sh"'
//                 }
//                 sh "ssh ${REMOTE_SERVER} 'bash -s' < /path/to/local/script.sh"
                sh "ssh ${REMOTE_SERVER} 'bash /root/myjenkins/mysky/jenkins.sh'"


            }
        }
    }

    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
    }
}
