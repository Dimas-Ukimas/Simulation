# Simulation :video_game:

This project demonstrate a virtual step-by-step wild 2D world comprises herbivores, predators, trees, grass and rocks.

## Description :memo:

Game map is a grid of 15x15 cells with various objects randomly located on it.

All objects on map are divided into 2 groups: creatures and entites.

### Creatures :cat:

The creatures include predators and herbivores. Each creature has a target that it seeks to eat.

Creatures can spend their turn on 2 actions:
- Move to target:dart:
- Eat (or attack) target :yum:

Creatures can move only orthogonally. In order to eat (attack) target, it needs to stand on adjacent cell to its food.

Predators hunt herbivores, and when they reach them, they try to attack. If target's hit points is less then predator's attack power, the herbivore perishes. Otherwise, target's hit points decrease by the amount of predator's attack power and herbivore remains alive. Also predators have a double herbivores movement speed.

Herbivores are looking for grass and consume it instantly when they get close enough.

**Path finding :triangular_ruler:**

Creatures uses the A* (A star) algorithm to find a path to the nearest target.

More about algorithm [here](https://vitalissius.github.io/A-Star-Pathfinding-for-Beginners/).

### Entities :cactus:

There are the following static objects on map: grass, trees, rocks.

Rocks and trees serves only as a natural obstacles, creatures cannot interact with them. Grass is a food for herbivores, which disappears from map when they eat it. The rest of the time it is the same obstacle as other entities.

### End game conditions :x:

There are few scenarios for the end of the game:
1. Predators exterminates all herbivores.
2. Herbivores extirpates all grass.
3. Dead lock (creatures have relevant targets on map, but they are unreachable).
4. Game loop is terminated manually by the user.

**About dead lock :lock::**

Sometimes the creatures on map spawns at spots surrounded by obstacles.

For example, a herbivore may be blocked by trees and rocks, so that it has no way to move. In the case when other herbivores are already dead, but the grass still exist, this becomes a "dead lock". A herbivore cannot get to the grass, and predators cannot get to this herbivore respectively. Since the game cannot end by its own, it will terminated automatically with appropriate message.

**Endless mode** :repeat:

User console have an endless mode switcher, which allows user to turn on/off this mode. If mode is enabled, creatures and their targets will spawn up to max limit (depends on the map size) every time they reach min limit. Thus, the game will last contineously until the mode is disabled or game is terminated manually.


## User interface & view :computer:

The program has a console view with a several user commands:

![Game view](https://github.com/Dimas-Ukimas/Simulation/blob/master/src/main/resources/Game%20view.png)

Object icons:
- ¥ - grass :herb:
- ▀ - rock :mount_fuji:
- φ - tree :deciduous_tree:
- P - predator :tiger:
- H - herbivore :cow2:



## Usage :rocket:

In order to launch the program you need to do next steps:
1. Download ZIP archive with code files and extract its content to your computer
2. Open command-line (for Windows it will be "Windows Key + R", then type "cmd") and procced to directory with downloaded program via command **cd *path to program folder on your device***
3. Execute **mvn clean install**
4. Execute **java -jar target/Simulation.jar**
5. Enjoy the game :blush:
