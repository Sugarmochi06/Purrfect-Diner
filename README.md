# My Project Proposal 
## Cozy Cooking Game
My application will be a simple cooking game in which the player will *recieve* orders from customers, *cook* the order, and *serve* the orders back to the customer. Anyone who would like to play a cooking game can use my application. This project interests me because i want to go into game development as a future career, and cozy cooking or simulation games has always been a type of game i enjoy.

## User Story
- As a user, I want to be able to add a customer's order to my tab of orders.
- As a user, I want to be able to view my current list of orders that i need to cook.
- As a user, I want to be able to remove a order from my current list of orders after I serve the order.
- As a user, I want to add view the recipe for a dish and add said ingredients into a bowl. 
- As a user, I want to be able to save my current list of orders.
- As a user, I want to be able to reload my current list of orders after i quit and renter the program.

## Instructions for End User
**Welcome to Purrfect Diner's Game Instructions!** 

- To start the Game, you can either click New game or Continue game. 
- Once the game is started, you will see an order on the left side of your screen. 
- To add the order, click on the Tab at the right side of the screen
- You will then be promted to either add or not add your order 
- **NOTE**: you will have to add the order in order to proceed to the order creation stage of the game. 
(sidenote: picking yes or no in the page asking you to add orders does not have different effects, both options will add your order. You just have to pick an option)
- After adding the order to the tab, you then have the option to click the bowl at the bottom of the screen. 
- The bowl would then lead you to the "kitchen" where you can start adding ingredients to the bowl.
- If you forget the recipe (ingredients combo) to make a certian dish, you can click the "check recipe" button to check the recipe for your order 
- Click on the ingredient you want to add on the right side of the screen to add an ingredient to your bowl. 
- If you made a mistake in adding an ingredient, you would have to clear out the entire bowl (sorry :( ). 
- To clear out the bowl, click the clear bowl button to clear out your bowl. 
- Once you have added all the ingredients required for your order, you can click the serve order button. 
- If your order is served succsesfully (meaning that you got all the ingredients right) you will be brought back to the "resturant" where you will then recive a new order. 
- If your order was served unsuccesfully (meaning that you had one or more incorrect ingredients), then you will be brought back to the "kitchen" again, where you will have to clear your bowl (unfortunately) and re-add your correct ingredients. 
- If you would like to quit the game, you can click the menu button. 
- When you click quit, you will be asked if you would like to save the game. 
- **Note**: when you save a game, no matter what stage you are at, when you click contiue game the next time you play, only your order will be saved. This means that you will have to add your ingredients again, no matter if you previously added ingredients. 

- **NOTE** : all graphical components in this game (background, button icons, etc) are hand drawn by me, they can be found by asking me. 

## Phase 4: Task 2 

Fri Mar 28 00:50:50 PDT 2025
model.Order@380a412b added to tab


Fri Mar 28 00:50:50 PDT 2025
retrieved all orders from tab


Fri Mar 28 00:50:51 PDT 2025
retrieved all orders from tab


Fri Mar 28 00:50:52 PDT 2025
retrieved all orders from tab


Fri Mar 28 00:50:52 PDT 2025
retrieved all orders from tab


Fri Mar 28 00:50:53 PDT 2025
added beef to bowl


Fri Mar 28 00:50:54 PDT 2025
added salt to bowl


Fri Mar 28 00:50:55 PDT 2025
added lettuce to bowl


Fri Mar 28 00:50:55 PDT 2025
added cheese to bowl


Fri Mar 28 00:50:56 PDT 2025
cleared bowl


Fri Mar 28 00:50:57 PDT 2025
added beef to bowl


Fri Mar 28 00:50:57 PDT 2025
added lettuce to bowl


Fri Mar 28 00:50:58 PDT 2025
added salt to bowl


Fri Mar 28 00:50:58 PDT 2025
added cheese to bowl


Fri Mar 28 00:51:02 PDT 2025
retrieved all orders from tab


Fri Mar 28 00:51:02 PDT 2025
retrieved all orders from tab


Fri Mar 28 00:51:02 PDT 2025
removed model.Order@380a412b from tab


Fri Mar 28 00:51:02 PDT 2025
cleared bowl

**note**: sorry for all the Phase 4 : task 2 commits :( i kept finding parts i was disastisfied with after commiting

## Phase 4: Task 3

A thing that could be refactored is the getter methods in Order and Customer as there is a lot of duplication. I could put those methods in another class and make both Order and Customer extend the newly created class, or i could have just made Order extend Customer so that it recieves the getter methods in Customer. Looking at my ui classes, my LoadingScreen class is extremely long and hard to scroll through, mainly due to me putting everything into one class. The readablilty of my code can be enhanced by splitting this class into more focused smaller classes. For example, splitting the classes up based on its function. For example, i could have seperated each of the JPanels from loading Screen into their own class to avoid creating so many JPanel fields within Loading screen and to reduce the amount of scrolling and searching to increase readability. There is also a lot of duplication between Resturant and LoadingScreen as they are both used to contain all the code for the different uis. I could refactor duplicate code in Resturant and LoadingScreen such as the methods related to saving and loading data into a seperate class that Resturant and LoadingScreen can both extend. I could also refactor the duplicate lines of code in my yesButton and noButton action listeners in my LoadingScreen class into a seperate method to reduce duplication. Another thing that i realized after event logging was that i had to refactor so that my bowl was its own class. This caused a lack of efficient functionality between bowl and ingredient as i kind of forgot that i had a ingredients class that i could have used and called on. If i had more time and realized this mistake earlier, i would have used the ingredients class which would have increased the clarity of my code more.
