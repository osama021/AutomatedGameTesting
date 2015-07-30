package devforrest.mario.objects.base;


import java.awt.Point;

import devforrest.mario.FileWriter.WriteInFile;
import devforrest.mario.core.GameRenderer;
import devforrest.mario.core.animation.CollidableObject;
import devforrest.mario.core.animation.Sprite;
import devforrest.mario.core.sound.specific.MarioSoundManager22050Hz;
import devforrest.mario.core.tile.GameTile;
import devforrest.mario.core.tile.TileMap;
import devforrest.mario.objects.creatures.RedShell;





public class Creature extends CollidableObject {
	
	private static WriteInFile writer=new WriteInFile();
	
	protected static int xCollideOffset = 2; 
	protected static int offMapOffset = 15;
	protected static float GRAVITY = .0008f;
	protected static float gravityEffect = .22f; 
	
	// Wake up values are constants based on the number of tiles on the screen
	// that are used to determine when mario comes within range of a creature.
	// Used exclusively within GameRender.java.
	public static int WAKE_UP_VALUE_DOWN_RIGHT = 24;
	public static int WAKE_UP_VALUE_UP_LEFT = -3;
	
	/* 
	 * Creature Attributes:
	 * 
	 * Relevant:  A creature that is always relevant must be updated every frame. By default, no creature
	 * 			  is always relevant. 
	 * Alive:     A creature that is on the map is alive. All creatures start alive and can be killed using
	 * 			  the kill() method.
	 * Sleeping:  A creature that is sleeping has yet to be seen by the player. All creatures start out
	 * 			  sleeping, and can be woken up using wakeUp(). They cannot be put back to sleep.
	 * Flipped:   isFlipped is a flag used to determine when to change the animation of a creature to death.
	 * 			  For example, a goomba that is hopped on is 'flipped', then removed from the game.
	 * Item:      A creature that is an item represents an item the player can interact with.
	 * Platform:  A creature is a platform if it is a non-aligned moving object the player
	 * 			  and creatures can interact with. 
	 * Invisible: When a creature is invisible, it isn't drawn.
	 */
	private boolean isAlwaysRelevant; 
	private boolean isAlive; 
	private boolean isSleeping; 
	private boolean isFlipped;
	private boolean isItem;
	private boolean isPlatform;
	private boolean isInvisible;
	
	public Creature() { 
		this(0, 0, null);
		//writer.WriteToFile("Creature::Creature()");
	}
	
	public Creature(int pixelX, int pixelY) {
		
		this(pixelX, pixelY, null);
		//writer.WriteToFile("Creature::Creature(int pixelX, int pixelY)");
	}
	
	/**
	 * @effects Creates a new Creature at the given pixelX, pixelY position that is capable
	 * of producing sounds from the soundManager. 
	 * 
	 * True: Collidable, Alive, Sleeping, Flipped.
	 * False: OnScreen, Item, Platform, Relevant.
	 */
	public Creature(int pixelX, int pixelY, MarioSoundManager22050Hz soundManager) {
		super(pixelX, pixelY, soundManager);	
		//writer.WriteToFile("Creature::Creature(int pixelX, int pixelY, MarioSoundManager22050Hz soundManager)");
		
		setIsCollidable(true);
		isAlive = true;
		isSleeping = true;
		isFlipped = false;
		setIsOnScreen(false);
		isItem = false;
		isPlatform = false;
		isAlwaysRelevant = false;
	}
	
	/**
	 * @return true if this creature is a Platform, false otherwise.
	 */
	public boolean isPlatform() {
		
		//writer.WriteToFile("Creature::boolean isPlatform()");
		
		return isPlatform;
	}
	
	/**
	 * @modifies the platform status of this Creature.
	 */
	public void setIsPlatform(boolean isPlatform) {
		//writer.WriteToFile("Creature::setIsPlatform(boolean isPlatform)");
		this.isPlatform = isPlatform;
	}
	
	/**
	 * @return true if this creature is an Item, false otherwise.
	 */
	public boolean isItem() {
		//writer.WriteToFile("Creature::boolean isItem()");
		return isItem;
	}
	
	/**
	 * @modifies the item status of this Creature.
	 */
	public void setIsItem(boolean isItem) {
		//writer.WriteToFile("Creature::setIsItem(boolean isItem)");
		this.isItem = isItem;
	}
	
	/**
	 * @return true if this creature is flipped, false otherwise.
	 */
	public boolean isFlipped() {
		//writer.WriteToFile("Creature::boolean isFlipped()");
		return isFlipped;
	}
	
	/**
	 * @modifies the flipped status of this Creature.
	 */
	public void setIsFlipped(boolean isFlipped) {
		//writer.WriteToFile("Creature::setIsFlipped(boolean isFlipped)");
		this.isFlipped = isFlipped;
	}
	
	/**
	 * @return true if this creature is sleeping, false otherwise.
	 */
	public boolean isSleeping() {
		//writer.WriteToFile("Creature::boolean isSleeping()");
		return isSleeping;
	}
	
	/**
	 * @modifies the sleeping status of this creature to false.
	 */
	public void wakeUp() { 
		//writer.WriteToFile("Creature::wakeUp()");
		isSleeping = false;
	}
	
	/**
	 * @return true if this creature is alive, false otherwise.
	 */
	public boolean isAlive() {
		//writer.WriteToFile("Creature::boolean isAlive()");
		return isAlive;
	}
	
	/**
	 * @modifies the life state of this creature (alive or dead) to dead.
	 */
    public void kill() {
    	//writer.WriteToFile("Creature::kill()");
    	isAlive = false;
    }
	
	/**
	 * @return true if this creature is a Platform, false otherwise.
	 */
	public boolean isAlwaysRelevant() {
		//writer.WriteToFile("Creature::boolean isAlwaysRelevant()");
		return isAlwaysRelevant;
	}
	
	/**
	 * @modifies the platform status of this Creature.
	 */
	public void setIsAlwaysRelevant(boolean isAlwaysRelevant) {
		//writer.WriteToFile("Creature::setIsAlwaysRelevant(boolean isAlwaysRelevant)");
		this.isAlwaysRelevant = isAlwaysRelevant;
	}
	
	/**
	 * @return true if this creature is invisible, false otherwise.
	 */
	public boolean isInvisible() {
		//writer.WriteToFile("Creature::boolean isInvisible()");
		return isInvisible;
	}
	
	/**
	 * @modifies the invisible status of this Creature.
	 */
	public void setIsInvisible(boolean isInvisible) {
		//writer.WriteToFile("Creature::setIsInvisible(boolean isInvisible)");
		this.isInvisible = isInvisible;
	}
    
	
	public void jumpedOn() {//writer.WriteToFile("Creature::jumpedOn()"); }
		
	}
	public void flip() {//writer.WriteToFile("Creature::flip()"); }
		
	}
	
	// for tile collisions
	public void xCollide(Point p) {
		//writer.WriteToFile("Creature::xCollide(Point p)");
		
		if(dx > 0) {
			x = x - xCollideOffset;
		} else {
			x = x + xCollideOffset;
		}
		dx = -dx;
	}
	
	// for creature collisions
	public void creatureXCollide() {
		//writer.WriteToFile("Creature::creatureXCollide()");
		
		if(dx > 0) {
			x = x - xCollideOffset;
		} else {
			x = x + xCollideOffset;
		}
		dx = -dx;
	}
	
	/**
	 * Calculates the type of collision in the X direction between a Tile 
	 * and a Sprite given the Sprite is currentely colliding with the tile. 
	 * This method relies on the general heuristic that if two 
	 * rectangular objects are colliding, then one object is not completely
	 * contained in the other. Because the colliding objects stick out, we
	 * know the direction of the collision. 
	 * 
	 * pre-condition: sprite is colliding with tile.
	 * @return Collision.WEST if sprite is colliding with the tile from the west or
	 * Collision.EAST if sprite is colliding with the tile from the east.
	 */
	public static Collision tileCollisionX(GameTile tile, Sprite s) {
		//writer.WriteToFile("Creature::Collision tileCollisionX(GameTile tile, Sprite s)");
		if(s.getX() > tile.getPixelX()) {
			return Collision.WEST;
		} else {
			return Collision.EAST;
		}
	}
	
	/**
	 * Calculates the type of collision in the Y direction between a Tile 
	 * and a Sprite given the Sprite is currentely colliding with the tile. 
	 * This method relies on the general heuristic that if two 
	 * rectangular objects are colliding, that one object is not completely
	 * contained in the other. Because the colliding objects stick out, we
	 * know the direction of the collision. 
	 * 
	 * pre-condition: sprite is colliding with tile.
	 * @return Collision.NORTH if sprite is colliding with the tile from the north or
	 * Collision.SOUTH if sprite is colliding with the tile from the south.
	 */
	public static Collision tileCollisionY(GameTile tile, Sprite s) {
		
		//writer.WriteToFile("Creature::Collision tileCollisionY(GameTile tile, Sprite s)");
		
		if(s.getY() < tile.getPixelY()) {
			return Collision.NORTH;
		} else {
			return Collision.SOUTH;
		}
	}
	
	public void updateCreature(TileMap map, int time) {
		
		//writer.WriteToFile("Creature::updateCreature(TileMap map, int time)");
		
		if(dy < gravityEffect) { // apply gravity...this must be done first
			dy = dy + GRAVITY * time;
		}
		//System.out.println("creature : "+x+" - "+y);
		float dx = this.dx;
		float oldX = this.x;
		float newX = oldX + dx * time;
		
		float dy = this.dy;
		float oldY = this.y;
		float newY = oldY + dy * time; 
		
		if(!isFlipped) {
			Point xTile = GameRenderer.getTileCollision(map, this, x, y, newX, y);
			Point yTile = GameRenderer.getTileCollision(map, this, x, y, x, newY);
			
			this.update(time);
			
			// Update collisions in the x-direction.
			if(oldX < -offMapOffset || oldX > GameRenderer.tilesToPixels(map.getWidth()) + offMapOffset) { // offscreen
				kill();
			} else {
				if(xTile == null) {
					x = newX;
				} else {
					if(!xTile.equals(yTile)) { // Only manage x-collisions that are not y-collisions
						this.xCollide(xTile);
						if(dx > 0) {
							x = GameRenderer.tilesToPixels(xTile.x) - this.getWidth();
						} else if (dx < 0) {
							x = GameRenderer.tilesToPixels(xTile.x + 1);
						}
					}
				}
			}
	
			// Update collisions in the y-direction. 
			if(oldY > GameRenderer.tilesToPixels(map.getHeight()) + offMapOffset) { // offscreen
				kill();
			} else {
				if(yTile == null) {
					y = newY;
				} else {
					if(dy > 0) {
						// mark this creature as colliding with a tile
						map.getTile(yTile.x, yTile.y).collidingCreatures().add(this); 
						GameTile tileRight = map.getTile(((int) yTile.getX()) + 1, ((int) yTile.getY()));
						if(tileRight != null) {
							tileRight.collidingCreatures().add(this);
						}
						y = GameRenderer.tilesToPixels(yTile.y) - this.getHeight();
					} else if (dy < 0) {
						y = GameRenderer.tilesToPixels(yTile.y + 1);
						this.dy = -dy/4; // fall faster if a collision occured
					} 
				}
			}
		} else { // flipped
			x = newX;
			y = newY;
			this.update(time);
		}
	}
	
	// Determines what happens when two different creatures collide.
	// Uncommenting the onSreen condition makes this more efficient, but more buggy
	public void creatureCollision(Creature creature) {
		
		//writer.WriteToFile("Creature::creatureCollision(Creature creature)");
		
		if(!this.isItem && !creature.isItem && !this.isPlatform && !creature.isPlatform &&
				this != creature && this.isCollidable() && creature.isCollidable()) {
			
			boolean collision = isCollision(this, creature);
			if(collision) {	
				// Handeling RedShell collision cases....
				// ______________________________________
				// creature 1 is a RedShell, creature 2 is not.
				if(this instanceof RedShell && !(creature instanceof RedShell)) {
					if(((RedShell) this).isMoving()) {
						creature.flip();
						soundManager.playKick();
					}
				// creature 2 is a RedShell, creature 1 is not.
				} else if(!(this instanceof RedShell) && creature instanceof RedShell) {
					if(((RedShell) creature).isMoving()) {
						this.flip();
						soundManager.playKick();
					}
				// both creature 1 and creature 2 are RedShells
				} else if(this instanceof RedShell && creature instanceof RedShell) {
					//RedShell 1 is moving, RedShell 2 is not.
					this.flip();
					creature.flip();
					soundManager.playKick();
				// End of RedShell collision cases...
			    //____________________________________________
					
			    } else {
			    	this.creatureXCollide();
			    	creature.creatureXCollide();
			    }
			}
		}
	}
}
