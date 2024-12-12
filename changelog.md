# project changelog

- ### [version] - day/month/year
  - #### changes
    - option
  - #### fixes
  - #### additions


- ### [0.2.6] - ?/?/2024
  - #### changes
    - Player Orientation gets updated to server now
    - Player Collision checker now works more accurate
    - each online player has its own sequent of frames now in onlineRenderer


- - - 
 

- ### [0.2.5] - 11/11/2024
  - #### changes
    - not containing mirror images anymore instead using a mirror function!
    - Using PlayerUpdate Class to update Player State
  - #### fixes
  - jump logics now work great
  - movement handler now works great in finding the floor
  - when jump the state of player stays jumping, so we see the jumping frames
  - using SuitHandler Class as the main Loader of all suits
  - showing other clients with their real Suits!
  - reading mirror images recurse now which makes sense now.
  - #### additions
  - attacking button (space)
  - attacking frames
  - idle_left makes it more friendly
- - - 
- ### [0.0.2.4] - 9/11/2024
  - #### additions
    - new characters
    - main loader class for handling all loadings in program
    - SuitLoader class for handling all suit loadings
    - option to choose suit in online menu panel!
- - -
- ### [0.0.2.3] - 7/11/2024
  - #### fixes
    - new clients get informed of other players in game
- - -
- ### [0.0.2.2] - 7/11/2024
  - #### fixes
    - movement report to server is improved
    - Color name is properly rendering online
  - #### changes
    - removed Offline Mode Completely
- - -
- ### [0.0.2.1] - 7/11/2024
  - #### additions
    - messages package of protocol buffers
    - OnlineRenderer Class for rendering online stuff
    - server-client communication
  - #### changes
    - using PlayerSuit Class instead of PlayerImages Class
- - - 
- ### [0.0.2.0] - 27/10/2024
  - #### additions
    - well implemented the menu
    - well implemented the `ConnectionInitializer.Class`
> [!NOTE]
> Format of messages on socket is not defined yet!
- - - 
- ### [0.0.1.0] - 25/10/2024
  - #### changes
    - better strategy in switching pages
  - #### additions
    - menu for offline and online play
- - -