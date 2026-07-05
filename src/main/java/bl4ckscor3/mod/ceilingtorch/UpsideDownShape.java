package bl4ckscor3.mod.ceilingtorch;

import org.joml.Vector3i;

import com.mojang.math.OctahedralGroup;
import com.mojang.math.SymmetricGroup3;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.ArrayVoxelShape;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.CubeVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UpsideDownShape {
	public static VoxelShape create(final VoxelShape shape) {
		OctahedralGroup rotation = OctahedralGroup.INVERT_Y;
		DiscreteVoxelShape newDiscreteShape = rotateShape(shape.shape, rotation);
		if (shape instanceof CubeVoxelShape) {
			return new CubeVoxelShape(newDiscreteShape);
		}
		else {
			Vec3 rotationPoint = new Vec3(0.5, 0.5, 0.5);
			Direction.Axis newX = permuteAxis(rotation.permutation, Direction.Axis.X);
			Direction.Axis newY = permuteAxis(rotation.permutation, Direction.Axis.Y);
			Direction.Axis newZ = permuteAxis(rotation.permutation, Direction.Axis.Z);
			DoubleList newXs = shape.getCoords(newX);
			DoubleList newYs = shape.getCoords(newY);
			DoubleList newZs = shape.getCoords(newZ);
			boolean flipX = rotation.inverts(Direction.Axis.X);
			boolean flipY = rotation.inverts(Direction.Axis.Y);
			boolean flipZ = rotation.inverts(Direction.Axis.Z);
			return new ArrayVoxelShape(
				newDiscreteShape,
				flipAxisIfNeeded(newXs, flipX, rotationPoint.get(newX), rotationPoint.x),
				flipAxisIfNeeded(newYs, flipY, rotationPoint.get(newY), rotationPoint.y),
				flipAxisIfNeeded(newZs, flipZ, rotationPoint.get(newZ), rotationPoint.z)
			);
		}
	}

	private static DiscreteVoxelShape rotateShape(DiscreteVoxelShape shape, OctahedralGroup rotation) {
		Vector3i v = rotateOctahedralGroup(rotation, new Vector3i(shape.xSize, shape.ySize, shape.zSize));
		int shiftX = fixupCoordinate(v, 0);
		int shiftY = fixupCoordinate(v, 1);
		int shiftZ = fixupCoordinate(v, 2);
		DiscreteVoxelShape newShape = new BitSetDiscreteVoxelShape(v.x, v.y, v.z);

		for (int x = 0; x < shape.xSize; ++x) {
			for (int y = 0; y < shape.ySize; ++y) {
				for (int z = 0; z < shape.zSize; ++z) {
					if (shape.isFull(x, y, z)) {
						Vector3i newPos = rotateOctahedralGroup(rotation, v.set(x, y, z));
						int newX = shiftX + newPos.x;
						int newY = shiftY + newPos.y;
						int newZ = shiftZ + newPos.z;
						newShape.fill(newX, newY, newZ);
					}
				}
			}
		}

		return newShape;
	}

	private static Vector3i rotateOctahedralGroup(OctahedralGroup rotation, final Vector3i v) {
		permuteVector(rotation.permutation, v);
		v.x *= rotation.invertX ? -1 : 1;
		v.y *= rotation.invertY ? -1 : 1;
		v.z *= rotation.invertZ ? -1 : 1;
		return v;
	}

	private static Vector3i permuteVector(SymmetricGroup3 permutation, final Vector3i v) {
		int v0 = v.get(permutation.permutation(0));
		int v1 = v.get(permutation.permutation(1));
		int v2 = v.get(permutation.permutation(2));
		return v.set(v0, v1, v2);
	}

	private static int fixupCoordinate(final Vector3i v, final int index) {
		int value = v.get(index);
		if (value < 0) {
			v.setComponent(index, -value);
			return -value - 1;
		}
		else {
			return 0;
		}
	}

	private static Direction.Axis permuteAxis(SymmetricGroup3 permutation, final Direction.Axis axis) {
		return Direction.Axis.VALUES[permutation.permutation(axis.ordinal())];
	}

	private static DoubleList flipAxisIfNeeded(final DoubleList newAxis, final boolean flip, final double newRelative, final double oldRelative) {
		if (!flip && newRelative == oldRelative) {
			return newAxis;
		}
		else {
			int size = newAxis.size();
			DoubleList newList = new DoubleArrayList(size);
			if (flip) {
				for (int i = size - 1; i >= 0; --i) {
					newList.add(-(newAxis.getDouble(i) - newRelative) + oldRelative);
				}
			}
			else {
				for (int i = 0; i >= 0 && i < size; ++i) {
					newList.add(newAxis.getDouble(i) - newRelative + oldRelative);
				}
			}

			return newList;
		}
	}
}
