package valoeghese.valoeghesesbe.world.trees.enumTypes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.util.EnumFacing;

public enum EnumDirection
{
	
	NORTH_(0, -1, EnumFacing.NORTH, null),
	SOUTH_(0, 1, EnumFacing.SOUTH, null),
	EAST_(1, 0, EnumFacing.EAST, null),
	WEST_(-1, 0, EnumFacing.WEST, null),
	UP_(0, 1, 0, EnumFacing.UP, null),
	DOWN_(0, -1, 0, EnumFacing.DOWN, null),
	VERTICAL(0, 0, EnumFacing.UP, null),
	VERTICAL_TRUE(0, 0, EnumFacing.UP, VERTICAL),
	NORTH(0, -1, EnumFacing.NORTH, EnumDirection.SOUTH_),
	SOUTH(0, 1, EnumFacing.SOUTH, EnumDirection.NORTH_),
	EAST(1, 0, EnumFacing.EAST, EnumDirection.WEST_),
	WEST(-1, 0, EnumFacing.WEST, EnumDirection.EAST_),
	UP(0, 1, 0, EnumFacing.UP, DOWN_),
	DOWN(0, -1, 0, EnumFacing.DOWN, UP_);
	
	private final int xOffset;
	private final int yOffset;
	private final int zOffset;
	private final EnumFacing facing;
	private final EnumDirection opposite;
	
	private EnumDirection(int x, int z, EnumFacing facing, EnumDirection opposite)
	{
		
		this.xOffset = x;
		this.yOffset = 0;
		this.zOffset = z;
		this.facing = facing;
		this.opposite = opposite;
		
	}
	private EnumDirection(int x, int y, int z, EnumFacing facing, EnumDirection opposite)
	{
		
		this.xOffset = x;
		this.yOffset = y;
		this.zOffset = z;
		this.facing = facing;
		this.opposite = opposite;
		
	}
	
	public int getXOffset()
	{
		return this.xOffset;
	}
	public int getYOffset()
	{
		return this.yOffset;	
	}
	public int getZOffset()
	{
		return this.zOffset;	
	}
	
	public float getXOffsetAsFloat()
	{
		return (float)this.xOffset;
	}
	
	public float getZOffsetAsFloat()
	{
		return (float)this.zOffset;	
	}
	
	public EnumFacing getEnumFacing()
	{
		return this.facing;
	}
	
	public EnumDirection makeFull()
	{
		switch (this)
		{
		case NORTH_:
			return NORTH;
		case EAST_:
			return EAST;
		case SOUTH_:
			return SOUTH;
		case WEST_:
			return WEST;
		case UP_:
			return UP;
		case DOWN_:
			return DOWN;
		case VERTICAL:
			return VERTICAL_TRUE;
		default:
			return this;
		}
	}
	
	public EnumDirection getOpposite()
	{
		return this.opposite;
	}
	
	public EnumDirection getFullOpposite()
	{
		return this.opposite.makeFull();
	}
	
	public static EnumDirection getRandomEnumType(Random rand)
	{
		
		switch (rand.nextInt(5))
		{
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		case 4:
			return VERTICAL;
		default:
			return null;
		}
		
	}
	public static EnumDirection getRandomFullEnumType(Random rand)
	{
		
		switch (rand.nextInt(6))
		{
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		case 4:
			return UP;
		case 5:
			return DOWN;
		default:
			return null;
		}
		
	}
	public static EnumDirection getRandomEnumDirectional(Random rand)
	{
		
		switch (rand.nextInt(4))
		{
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		default:
			return null;
		}
		
	}
	
	public static EnumDirection getRandomEnumVertical(Random rand)
	{
		
		switch (rand.nextInt(3))
		{
		case 0:
			return UP;
		case 1:
			return DOWN;
		case 2:
			return VERTICAL;
		default:
			return null;
		}
		
	}
	
	public static EnumDirection getRandomEnumX(Random rand)
	{
		
		switch (rand.nextInt(3))
		{
		case 0:
			return EAST;
		case 1:
			return WEST;
		case 2:
			return VERTICAL_TRUE;
		default:
			return null;
		}
		
	}
	public static EnumDirection getRandomEnumZ(Random rand)
	{
		
		switch (rand.nextInt(3))
		{
		case 0:
			return NORTH;
		case 1:
			return SOUTH;
		case 2:
			return VERTICAL_TRUE;
		default:
			return null;
		}
		
	}
	
	public static EnumDirection getRandomEnumXDirectional(Random rand)
	{
		
		switch (rand.nextInt(2))
		{
		case 0:
			return EAST;
		default:
			return WEST;
		}
		
	}
	public static EnumDirection getRandomEnumZDirectional(Random rand)
	{
		
		switch (rand.nextInt(2))
		{
		case 0:
			return NORTH;
		default:
			return SOUTH;
		}
		
	}
	
	public EnumDirection getRandomOppAxisEnum(Random rand)
	{
		switch(this.facing)
		{
		case NORTH:
			return EnumDirection.getRandomEnumX(rand);
		case SOUTH:
			return EnumDirection.getRandomEnumX(rand);
		case EAST:
			return EnumDirection.getRandomEnumZ(rand);
		case WEST:
			return EnumDirection.getRandomEnumZ(rand);
		default:
			return EnumDirection.getRandomEnumDirectional(rand);
		}
	}
	
	public static ArrayList<EnumDirection> getEnumDirectionals()
	{
		
		ArrayList<EnumDirection> tempArray = new ArrayList<EnumDirection>();
		tempArray.add(NORTH);
		tempArray.add(EAST);
		tempArray.add(SOUTH);
		tempArray.add(WEST);
		return tempArray;
		
	}
	
	public EnumAxis getLogAxisOf()
	{
		switch(this.facing)
		{
		case NORTH:
			return EnumAxis.Z;
		case SOUTH:
			return EnumAxis.Z;
		case EAST:
			return EnumAxis.X;
		case WEST:
			return EnumAxis.X;
		default:
			return EnumAxis.Y;
		}
	}

}
