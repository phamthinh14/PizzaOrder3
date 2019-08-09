# PizzaOrder3 - Online Pizza Ordering

  This is an online pizza ordering system like Papa John's Pizza Delivery. <br>
  This application is built with Java, Javascript, HTML, CSS, and Bootstrap. <br>
  There are Spring Boot for the html templates, and Spring Security for user to login to view their orders and collect information of new users. <br>
  Furthermore, Jquery is used to decorate and create animation.<br>
  Administrator can switch database from H2 to MySQL or PostgreSQL<br>

To build their pizza, the user can select:<br>
The Pizza's Size<br>
A Dough Type<br>
Sauce<br>
Cheese<br>
Veggies<br>
Toppings/Finishing touches<br>
User can Update, View the detail of each orders and all orders, and delete their order<br>
Links:<br>
https://sleepy-falls-50987.herokuapp.com/
*NOTES: Fixed Heroku issued<br>
This is the website to properly deploy a java application to heroku. Follow the steps and ensure that at least one instance of the app is running by the line of command below:<br>
heroku ps:scale web=1

# HomeController Methods
 @RequestMapping("/")<br>
 public String land(): This method will take user to a page with two button Menu and Login with a background picture.<br>
 @RequestMapping("/order")<br>
 public String homePage(Model model): This method will take the user to the home.html to view all their orders<br>
 @RequestMapping("/sendemail")
 public String sendEmail(): This method will take the user to the mailTemplate.html to confirm their orders' summary is sent the email that they provide when the customers register<br>
 @RequestMapping("/adminview")<br>
 public String adminView(Model model): This method is for the Admin only, and it will display all the information of registered customers. This method will take the admin to the admindisplayCustomersInfo.html<br>
 @RequestMapping("/adminedit")<br>
 public String adminEdit(Model model): This method is for the Admin only, and it will display all customers' orders. The admin can update, view, and delete orders. Furthermore, admin will know the the total sales of the day, and top three most ordered toppings.<br>
 This method will take the admin to the adminEditCustomersOrder.html<br>
 private String findTopToppings(): This method is to count when the user selected a toppings. The count will then pass in sortByValue(HashMap<String, Integer> hm) to sort and return a Hashmap<String, Integer>. The top three of that hashmap will then be added to String result<br>
 public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm): This method will sort the toppings from highest to lowest and return a hashmap<br>
 private double totalSales(): This method will loop through all the price of orders and add them to return the total sales of the day<br>
@RequestMapping("/inputusername")<br>
public String userNameInputForm(): This method will allow an admin to input a String of username to find. It will take the user to the userNameInputForm.html.<br>
userNameInputForm.html will then ask the user to enter their username and then pass that String to String findUserName(@RequestParam("nameToFind") String nameToFind, Model model) 
@RequestMapping("/processinputusername")<br>
public String findUserName(@RequestParam("nameToFind") String nameToFind, Model model): When the admin submit that username(String) it will be pass to this method to find that user. When we found a User, it will display first and last name, phone number, and email of that user. If the user is not found in the database, it will print "User is not found"<br>
    @GetMapping("/add")<br>
    public String orderForm(Model model): This method will take the user to the orderform.html and ask the user to create their pizzas<br>
    @PostMapping("/process")<br>
    public String processOrderForm(@Valid PizzaOrder pizzaOrder): This method will acquire selected options and print out the prices<br>
    @RequestMapping("/detail/{id}")<br>
    public String showDetailPizza(@PathVariable("id") long id, Model model): This method shows order's detail<br>
    @RequestMapping("/update/{id}")<br>
    public String updateMessage(@PathVariable("id") long id, Model model): This method will allow users to update their orders<br>
    @RequestMapping("/delete/{id}")<br>
    public String delMessage(@PathVariable("id") long id): This method will allow users to delete their orders<br>
        
The date/time of the order should also be captured.

Users can either log into their account to place and order or create an account upon checking out. Users should be able to see a list of their order history when logged into their accounts.

Reports to generate by the admin:

A list of all customers
Top 3 pizza toppings
Total sales
Find a customer by name
Admins should also have the ability to add/remove pizza toppings from the ordering system.
