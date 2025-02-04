/*
 * This file is part of Limbo.
 *
 * Copyright (C) 2022. LoohpJames <jamesloohp@gmail.com>
 * Copyright (C) 2022. Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loohp.limbo.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import com.loohp.limbo.world.BlockPosition;

import net.querz.nbt.io.NBTOutputStream;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.Tag;

public class DataTypeIO {
	
	public static void writeBlockPosition(DataOutputStream out, BlockPosition position) throws IOException {
        out.writeLong(((position.getX() & 0x3FFFFFF) << 38) | ((position.getZ() & 0x3FFFFFF) << 12) | (position.getY() & 0xFFF));
	}
	
	public static void writeUUID(DataOutputStream out, UUID uuid) throws IOException {
		out.writeLong(uuid.getMostSignificantBits());
		out.writeLong(uuid.getLeastSignificantBits());
	}

	public static UUID readUUID(DataInputStream in) throws IOException {
		return new UUID(in.readLong(), in.readLong());
	}
	
	public static void writeCompoundTag(DataOutputStream out, CompoundTag tag) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		
		DataOutputStream output = new DataOutputStream(buffer);
		new NBTOutputStream(output).writeTag(tag, Tag.DEFAULT_MAX_DEPTH);
		
		byte[] b = buffer.toByteArray();
	    out.write(b);
	}
	
	public static String readString(DataInputStream in, Charset charset) throws IOException {
		int length = readVarInt(in);

	    if (length == -1) {
	        throw new IOException("Premature end of stream.");
	    }

	    byte[] b = new byte[length];
	    in.readFully(b);
	    return new String(b, charset);
	}
	
	public static int getStringLength(String string, Charset charset) throws IOException {
	    byte[] bytes = string.getBytes(charset);
	    return getVarIntLength(bytes.length) + bytes.length;
	}
	
	public static void writeString(DataOutputStream out, String string, Charset charset) throws IOException {
	    byte[] bytes = string.getBytes(charset);
	    writeVarInt(out, bytes.length);
	    out.write(bytes);
	}
	
	public static int readVarInt(DataInputStream in) throws IOException {
	    int numRead = 0;
	    int result = 0;
	    byte read;
	    do {
	        read = in.readByte();
	        int value = (read & 0b01111111);
	        result |= (value << (7 * numRead));

	        numRead++;
	        if (numRead > 5) {
	            throw new RuntimeException("VarInt is too big");
	        }
	    } while ((read & 0b10000000) != 0);

	    return result;
	}
	
	public static void writeVarInt(DataOutputStream out, int value) throws IOException {
	    do {
	        byte temp = (byte)(value & 0b01111111);
	        // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
	        value >>>= 7;
	        if (value != 0) {
	            temp |= 0b10000000;
	        }
	        out.writeByte(temp);
	    } while (value != 0);
	}
	
	public static int getVarIntLength(int value) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(buffer);
	    do {
	        byte temp = (byte)(value & 0b01111111);
	        // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
	        value >>>= 7;
	        if (value != 0) {
	            temp |= 0b10000000;
	        }
	        out.writeByte(temp);
	    } while (value != 0);
	    return buffer.toByteArray().length;
	}
	
	public static long readVarLong(DataInputStream in) throws IOException {
	    int numRead = 0;
	    long result = 0;
	    byte read;
	    do {
	        read = in.readByte();
	        long value = (read & 0b01111111);
	        result |= (value << (7 * numRead));

	        numRead++;
	        if (numRead > 10) {
	            throw new RuntimeException("VarLong is too big");
	        }
	    } while ((read & 0b10000000) != 0);

	    return result;
	}
	
	public static void writeVarLong(DataOutputStream out, long value) throws IOException {
	    do {
	        byte temp = (byte)(value & 0b01111111);
	        // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
	        value >>>= 7;
	        if (value != 0) {
	            temp |= 0b10000000;
	        }
	        out.writeByte(temp);
	    } while (value != 0);
	}

}
