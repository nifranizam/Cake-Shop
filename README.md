Cake Shop

•	.adapters: This package contains the ItemListAdapter class responsible for displaying items in a recycler view within the main activity.
•	.config: This package contains configuration classes like DbConfig that defines database table and column names, and Validation that validates user inputs.
•	.database: This package contains the database related functionalities. It includes classes like ItemRepository for interacting with the database and THA_DB which is the Room database class.
•	.models: This package contains the data models used by the application. In this case, it has the Item class representing a product in the system.
•	.models: (This seems to be a duplicate entry, likely a typo). This should most likely point to .viewmodels which contains the ItemViewModel class for managing the item data using LiveData.
Activities:
•	MainActivity: This is the main activity of the application. It handles displaying the list of items using a recycler view and provides a floating action button to add new items. It also handles user interaction with the item list and launches the CakeFormActivity for adding/editing items.
•	CakeFormActivity: This activity is responsible for adding and editing items. It allows users to enter product details like name, description, and price. It also handles saving the data and updating the item list in the main activity.
Database:
The application uses a Room database named THA_DB to store product information. The Item class represents a product in the database with properties like id, name, description, and price.
Adding a New Item:
1.	User clicks the floating action button in the main activity.
2.	The MainActivity launches the CakeFormActivity with an intent flag indicating adding a new item.
3.	User enters product details in the form.
4.	User clicks the "Add" button.
5.	CakeFormActivity performs validation on the entered data.
6.	If validation is successful, it creates a new Item object with the entered details.
7.	CakeFormActivity calls the insertItem method of the ItemRepository to save the new item in the database.
8.	The ItemRepository in turn uses ItemDAO to insert the item into the Item_Infor table of the THA_DB database.
9.	Once the item is saved, CakeFormActivity sets the result of the intent to indicate successful insertion and finishes itself.
10.	The onActivityResult method in MainActivity receives the result and updates the item list displayed in the recycler view.

