
### **User Endpoints**

1.  **Register User**

    -   **POST** `/api/users/register`
    -   Request Body: `{ "name": "John Doe", "email": "john@example.com", "password": "securepassword" }`
    -   Response: User details (without password).
2.  **Login User**

    -   **POST** `/api/users/login`
    -   Request Body: `{ "email": "john@example.com", "password": "securepassword" }`
    -   Response: Authentication token (JWT).
3.  **Get User Profile**

    -   **GET** `/api/users/profile`
    -   Headers: `Authorization: Bearer <token>`
    -   Response: User profile details.
4.  **Update User Profile**

    -   **PUT** `/api/users/profile`
    -   Headers: `Authorization: Bearer <token>`
    -   Request Body: `{ "name": "John Updated", "email": "newemail@example.com" }`
    -   Response: Updated user profile.

----------

### **Transaction Endpoints**

1.  **Create a Transaction**

    -   **POST** `/api/transactions`
    -   Headers: `Authorization: Bearer <token>`
    -   Request Body:

        json

        Copy code

        `{
          "type": "Expense",
          "categoryId": 1,
          "amount": 150.0,
          "date": "2024-11-30",
          "description": "Groceries"
        }`

    -   Response: Created transaction.
2.  **Get All Transactions**

    -   **GET** `/api/transactions`
    -   Headers: `Authorization: Bearer <token>`
    -   Query Parameters (optional):
        -   `type=Expense`
        -   `categoryId=1`
        -   `startDate=2024-11-01&endDate=2024-11-30`
    -   Response: List of transactions.
3.  **Get a Single Transaction**

    -   **GET** `/api/transactions/{id}`
    -   Headers: `Authorization: Bearer <token>`
    -   Response: Transaction details.
4.  **Update a Transaction**

    -   **PUT** `/api/transactions/{id}`
    -   Headers: `Authorization: Bearer <token>`
    -   Request Body:

        json

        Copy code

        `{
          "type": "Expense",
          "categoryId": 1,
          "amount": 200.0,
          "date": "2024-11-30",
          "description": "Updated groceries"
        }`

    -   Response: Updated transaction.
5.  **Delete a Transaction**

    -   **DELETE** `/api/transactions/{id}`
    -   Headers: `Authorization: Bearer <token>`
    -   Response: Success message.
6.  **Get Monthly Summary**

    -   **GET** `/api/transactions/summary`
    -   Headers: `Authorization: Bearer <token>`
    -   Query Parameters: `month=2024-11`
    -   Response: `{ "totalIncome": 5000.0, "totalExpenses": 3000.0, "balance": 2000.0 }`

----------

### **Category Endpoints**

1.  **Create a Category**

    -   **POST** `/api/categories`
    -   Headers: `Authorization: Bearer <token>`
    -   Request Body: `{ "name": "Food", "description": "Expenses for food and dining" }`
    -   Response: Created category.
2.  **Get All Categories**

    -   **GET** `/api/categories`
    -   Headers: `Authorization: Bearer <token>`
    -   Response: List of categories.
3.  **Update a Category**

    -   **PUT** `/api/categories/{id}`
    -   Headers: `Authorization: Bearer <token>`
    -   Request Body: `{ "name": "Groceries", "description": "Updated description" }`
    -   Response: Updated category.
4.  **Delete a Category**

    -   **DELETE** `/api/categories/{id}`
    -   Headers: `Authorization: Bearer <token>`
    -   Response: Success message.

----------

### **Optional Advanced Endpoints**

1.  **Export Transactions**

    -   **GET** `/api/transactions/export`
    -   Headers: `Authorization: Bearer <token>`
    -   Query Parameters: Same as "Get All Transactions".
    -   Response: CSV file of transactions.
2.  **Search Transactions**

    -   **GET** `/api/transactions/search`
    -   Headers: `Authorization: Bearer <token>`
    -   Query Parameters: `query=groceries`
    -   Response: List of matching transactions.