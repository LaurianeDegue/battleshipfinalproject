# BattleCruiser

## Project Description

BattleCruiser or Battleship (as we usually call it) is a game where two opponents, place multiple ships(usually around 5 or so), of varying sizes around a 10 x 10 board.
The goal is to sink all of the opponents ships, essentially selecting each cell where the ship is in. Essentially my application is a recreation of just that.
However instead of playing against another person, the player would be playing against the computer. The players gets to choose, where to place all of their ships, as well as their orientation.
I pretty much only used Java to create this project, as well as some BootstrapFX, for some button styling!

I faced a lot of challenges, trying to understand how to make the application, especially since I've never made a game with Java before, I mostly struggled with my Board class.
I was dealing with Event Handlers, for the placement of the ships and it wasn't something that I was used to, so I definitely did struggle a lot at first, to understand what I was doing, and how to get the results that I wanted.
But after long, I did manage to figure it out. Another big issue, I had was with the restart button, I was able to clear the board, but not exactly start over 100% and it was really had to fully understand what I had to do to make it work.
But after another look at my code and at my classes, I managed to figure out the way to make a functioning restart button that wouldn't reset my scoreboard.

I'm really proud of what I managed to do, but there are still some things that I'd want to improve. Ideally, I would like to create a Text Field, that would let the users decide the theme or their boards.
Since I thought it would be a fun little user interaction aspect to have, everyone could have their own personalized board. I also would like to make the game more challenging, or even to  be able to pick different difficulties.
Since as of right now, our opponent, is simply the computer picking random cells inside of our board, creating an actual AI for the game, would be really interesting, especially if it would be possible to make it at different difficulties.
And the last thing, I'd like to work on in my project, is definitely the styling of my stage, I feel its very bland and empty, and the user menu, doesn't look like much, its not very pretty or eye catching.
It does what it's supposed to do, but I'd like it to be more interesting.


## Design

Essentially the role of each class in my program is very simple. 

First we have our Ship class,
Inside of our ship class, we are essentially defining the health and length of our ships as well as creating rectangles for each "hp bar" that the ship has. If the ship has a length of 5, we would create 5 rectangles.
We're also decreasing the hp, if the ship is hit, as well as returning whether or not the ship is alive.

Next up, we have our Board class,
In this class, we are defining whether the board is ours or the enemies, as well as the number of ships inside of the board. We are also creating a new class called Cell, that make up the board.
We are essentially, placing our ships, validating the placement of our ships as well as the validity of the cells that we are trying to place our ships in. As well as getting the cells themselves.

We also created an interface for our buttons, that was implemented by our main class, essentially we created methods for two different buttons. Restart and Quit

And lasty, we have our main class, we are combing both the board and ship class, to finally create our game. In this class, I'm also creating the rest of the items on the stage, like our scoreboard, instructions and our two buttons.

## UML Diagram
![Final BattleShip UML](https://user-images.githubusercontent.com/81866531/170390868-70c40d07-9b18-4fe1-a18a-92fa43f952d8.jpeg)

## Game examples
![image](https://user-images.githubusercontent.com/81866531/170391156-b420af02-9a3f-4a2c-a119-a0a68d44d9f1.png)
![image](https://user-images.githubusercontent.com/81866531/170391341-fc964bf2-e874-46e8-8df2-17de06498d61.png)
![image](https://user-images.githubusercontent.com/81866531/170391485-dea053c4-5499-43e8-9c66-3f2f768a7e26.png)
![image](https://user-images.githubusercontent.com/81866531/170391578-52ff7390-6efb-415e-b43b-5248e5a68ffe.png)
![image](https://user-images.githubusercontent.com/81866531/170391597-8601168f-4c24-48f3-992e-622cfa400db3.png)





