# Claims Processing & Credit Score Analysis System

A comprehensive microservices-based system for processing insurance claims and analyzing credit scores, built with Spring Boot and modern cloud technologies.

## 🏗️ System Architecture

This project consists of multiple microservices working together to provide a complete claims processing and credit analysis solution:

```
┌─────────────────────────────────────────────────────────────────┐
│                        API Gateway (Port: 8080)                 │
│                    - JWT Authentication                         │
│                    - Request Routing                           │
│                    - CORS Configuration                         │
└─────────────────────┬───────────────────────────────────────────┘
                      │
        ┌─────────────┼─────────────┐
        │             │             │
        ▼             ▼             ▼
┌─────────────┐ ┌─────────────┐ ┌─────────────┐
│User Service │ │Credit Score │ │Claims Proc. │
│(Port: 8082) │ │(Port: 8083) │ │(Port: 8081) │
│             │ │             │ │             │
│- Auth       │ │- Score Calc │ │- Claim Mgmt │
│- JWT Tokens │ │- History    │ │- Status     │
│- Profiles   │ │- Caching   │ │- Batch Jobs │
└─────────────┘ └─────────────┘ └─────────────┘
        │             │             │
        └─────────────┼─────────────┘
                      │
        ┌─────────────┼─────────────┐
        │             │             │
        ▼             ▼             ▼
┌─────────────┐ ┌─────────────┐ ┌─────────────┐
│   MySQL     │ │    Redis    │ │   Kafka     │
│  Database   │ │   Cache     │ │  Messaging  │
│             │ │             │ │             │
│- userdb     │ │- Sessions   │ │- Events     │
│- creditdb   │ │- Cache     │ │- Notifications│
│- claimsdb   │ │- Status    │ │- Streaming  │
└─────────────┘ └─────────────┘ └─────────────┘
```

### Core Services

1. **Claim Processing System** (Port: 8081)
   - Handles insurance claim submission, processing, and management
   - Real-time claim status updates and notifications
   - Batch processing for automated claim workflows

2. **Credit Score Analysis Tool** (Port: 8083)
   - Calculates and manages credit scores
   - Provides credit history analysis
   - Automated credit score updates and notifications

3. **User Management System** (Port: 8082)
   - User registration and authentication
   - JWT-based security
   - User profile management

4. **API Gateway** (Port: 8080)
   - Centralized routing and load balancing
   - JWT authentication and authorization
   - CORS configuration for cross-origin requests

## 🚀 Key Features

### Claims Processing System
- **Claim Management**: Create, read, update, and delete insurance claims
- **Status Tracking**: Real-time claim status updates with Redis caching
- **Batch Processing**: Automated claim processing with scheduled tasks
- **Notifications**: Kafka-based notification system for status changes
- **Email Integration**: Automated email notifications for claim updates
- **Reporting**: Comprehensive claim reports and analytics

### Credit Score Analysis
- **Score Calculation**: Advanced credit score calculation algorithms
- **History Tracking**: Complete credit score history and trends
- **Automated Updates**: Scheduled credit score recalculations
- **Caching**: Redis-based caching for improved performance
- **Integration**: Seamless integration with user management system

### User Management
- **Authentication**: Secure JWT-based authentication
- **Registration**: User registration with password encryption
- **Profile Management**: User profile and role management
- **Security**: BCrypt password hashing and secure token generation

### API Gateway
- **Routing**: Intelligent request routing to appropriate services
- **Security**: Centralized JWT validation and authorization
- **CORS**: Cross-origin resource sharing configuration
- **Load Balancing**: Request distribution across service instances

## 🛠️ Technology Stack

### Backend Technologies
- **Spring Boot 3.x**: Main application framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database operations and ORM
- **Spring Cloud Gateway**: API gateway and routing
- **Spring Kafka**: Message streaming and event processing

### Database & Caching
- **MySQL 8.0**: Primary relational database
- **Redis**: In-memory caching and session storage

### Message Streaming
- **Apache Kafka**: Event streaming and message queuing
- **Zookeeper**: Kafka coordination service

### Security
- **JWT (JSON Web Tokens)**: Stateless authentication
- **BCrypt**: Password hashing
- **OAuth2**: Google OAuth integration (configurable)

### Monitoring & Logging
- **Log4j2**: Advanced logging framework
- **Splunk Integration**: Log aggregation and analysis
- **Email Notifications**: Automated alerting system

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- MySQL 8.0
- Redis
- Apache Kafka

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd claims
```

### 2. Start Infrastructure Services
```bash
docker-compose up -d
```

This will start:
- MySQL database (Port: 3306)
- Redis cache (Port: 6379)
- Apache Kafka (Port: 9092)
- Zookeeper (Port: 2181)

### 3. Configure Database
The system will automatically create the required databases:
- `claimsdb` - For claims processing
- `creditdb` - For credit score analysis
- `userdb` - For user management

### 4. Start the Services

#### Start User Management Service
```bash
cd CreditScoreAnalysisTool/userms/userms
mvn spring-boot:run
```

#### Start Credit Score Service
```bash
cd CreditScoreAnalysisTool/credit/credit
mvn spring-boot:run
```

#### Start Claims Processing Service
```bash
cd ClaimProcessingSystem
mvn spring-boot:run
```

#### Start API Gateway
```bash
cd CreditScoreAnalysisTool/cloudgateway/cloudgateway
mvn spring-boot:run
```

## 🔧 Configuration

### Environment Variables
<<<<<<< HEAD
**IMPORTANT**: For security, all sensitive configuration is now externalized to environment variables.

#### 1. Copy Environment Template
```bash
cp env.example .env
```

#### 2. Set Required Environment Variables
```bash
# JWT Secret (CRITICAL - Change in production!)
export JWT_SECRET="your-super-secret-jwt-key-change-this-in-production"

# Database Credentials
export DB_USERNAME="root"
export DB_PASSWORD="your-secure-database-password"

# Email Configuration
export SPRING_MAIL_USERNAME="your-email@gmail.com"
export SPRING_MAIL_PASSWORD="your-app-password"

# Optional: Splunk Configuration
export SPLUNK_HEC_URI="https://your-splunk-instance:8088"
export SPLUNK_HEC_TOKEN="your-hec-token"
export SPLUNK_HEC_INDEX="your-index"

# Optional: OAuth2 Configuration
export GOOGLE_CLIENT_ID="your-client-id"
export GOOGLE_CLIENT_SECRET="your-client-secret"
```

#### 3. Security Notes
- **JWT_SECRET**: Must be at least 32 characters long and cryptographically secure
- **DB_PASSWORD**: Use strong, unique passwords for production
- **Email credentials**: Use application-specific passwords, not your main account password
=======
Update the following in your `application.properties` files:

#### Email Configuration
```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

#### Splunk Configuration (Optional)
```properties
splunk.hec.uri=https://your-splunk-instance:8088
splunk.hec.token=your-hec-token
splunk.hec.index=your-index
```

#### OAuth2 Configuration (Optional)
```properties
spring.security.oauth2.client.registration.google.client-id=your-client-id
spring.security.oauth2.client.registration.google.client-secret=your-client-secret
```
>>>>>>> 9f577294761947bca3d61e2974d264adb989cd57

## 📡 API Endpoints

### API Gateway (Port: 8080)
All requests should be made through the API Gateway:

#### User Management
- `POST /users/register` - User registration
- `POST /users/login` - User authentication
- `GET /users/{userId}` - Get user details

#### Credit Score Analysis
- `GET /score/{userId}` - Get credit score
- `POST /score/calculate` - Calculate new credit score
- `PUT /score/{userId}` - Update credit score
- `DELETE /score/{userId}` - Delete credit score

#### Claims Processing
- `POST /claims` - Submit new claim
- `GET /claims/{claimId}` - Get claim details
- `PUT /claims/{claimId}` - Update claim
- `DELETE /claims/{claimId}` - Delete claim
- `GET /claims/user/{userId}` - Get user's claims

## 🔐 Security Features

### JWT Authentication
- Stateless authentication using JWT tokens
- Token expiration and refresh mechanisms
- Secure token validation across services
<<<<<<< HEAD
- **Environment-based secret key management**
=======
>>>>>>> 9f577294761947bca3d61e2974d264adb989cd57

### Password Security
- BCrypt password hashing
- Secure password storage and validation

<<<<<<< HEAD
### Database Security
- **SSL/TLS encryption enabled** for all database connections
- **Environment-based credential management**
- Secure connection parameters

=======
>>>>>>> 9f577294761947bca3d61e2974d264adb989cd57
### CORS Configuration
- Configurable cross-origin resource sharing
- Support for multiple domains and environments

<<<<<<< HEAD
### Security Best Practices Implemented
- ✅ **No hardcoded secrets** - All sensitive data externalized
- ✅ **SSL/TLS encryption** - Database connections encrypted
- ✅ **Proper error handling** - No sensitive data in error messages
- ✅ **Structured logging** - Security events properly logged
- ✅ **Input validation** - All endpoints validate input data

=======
>>>>>>> 9f577294761947bca3d61e2974d264adb989cd57
## 📊 Monitoring and Logging

### Logging
- Structured logging with Log4j2
- Log rotation and archival
- Multiple log levels and appenders

### Monitoring
- Splunk integration for log aggregation
- Email notifications for critical events
- Performance metrics and monitoring

## 🔄 Message Processing

### Kafka Integration
- Event-driven architecture
- Asynchronous message processing
- Reliable message delivery and ordering

### Redis Caching
- High-performance caching layer
- Session management
- Real-time data access

## 🧪 Testing

### Unit Tests
Each service includes comprehensive unit tests:
```bash
mvn test
```

### Integration Tests
Test the complete system integration:
```bash
mvn integration-test
```

## 📈 Performance Features

### Caching Strategy
- Redis-based caching for frequently accessed data
- Cache invalidation and refresh mechanisms
- Performance optimization for high-traffic scenarios

### Batch Processing
- Scheduled batch jobs for claim processing
- Automated credit score updates
- Efficient resource utilization

## 🚀 Deployment

### Docker Deployment
```bash
# Build all services
docker-compose build

# Start all services
docker-compose up -d
```

### Production Considerations
- Configure proper database connection pooling
- Set up monitoring and alerting
- Implement backup and recovery strategies
- Configure load balancing and scaling

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review the API endpoints and configuration

## 🔮 Future Enhancements

- Microservices orchestration with Kubernetes
- Advanced analytics and reporting
- Machine learning integration for fraud detection
- Real-time dashboard and monitoring
- Mobile application support
- Advanced security features and compliance

---

**Note**: This is a comprehensive microservices system designed for production use. Ensure proper configuration of all external services and security settings before deployment.
