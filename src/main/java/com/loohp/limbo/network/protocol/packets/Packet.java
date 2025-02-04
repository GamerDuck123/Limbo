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

package com.loohp.limbo.network.protocol.packets;

import java.util.Map;

public class Packet {

	private static Map<Integer, Class<? extends PacketIn>> handshakeIn;

	private static Map<Integer, Class<? extends PacketIn>> statusIn;
	private static Map<Class<? extends PacketOut>, Integer> statusOut;

	private static Map<Integer, Class<? extends PacketIn>> loginIn;
	private static Map<Class<? extends PacketOut>, Integer> loginOut;

	private static Map<Integer, Class<? extends PacketIn>> playIn;
	private static Map<Class<? extends PacketOut>, Integer> playOut;

	public static Map<Integer, Class<? extends PacketIn>> getHandshakeIn() {
		return handshakeIn;
	}

	public static void setHandshakeIn(Map<Integer, Class<? extends PacketIn>> handshakeIn) {
		Packet.handshakeIn = handshakeIn;
	}

	public static Map<Integer, Class<? extends PacketIn>> getStatusIn() {
		return statusIn;
	}

	public static void setStatusIn(Map<Integer, Class<? extends PacketIn>> statusIn) {
		Packet.statusIn = statusIn;
	}

	public static Map<Class<? extends PacketOut>, Integer> getStatusOut() {
		return statusOut;
	}

	public static void setStatusOut(Map<Class<? extends PacketOut>, Integer> statusOut) {
		Packet.statusOut = statusOut;
	}

	public static Map<Integer, Class<? extends PacketIn>> getLoginIn() {
		return loginIn;
	}

	public static void setLoginIn(Map<Integer, Class<? extends PacketIn>> loginIn) {
		Packet.loginIn = loginIn;
	}

	public static Map<Class<? extends PacketOut>, Integer> getLoginOut() {
		return loginOut;
	}

	public static void setLoginOut(Map<Class<? extends PacketOut>, Integer> loginOut) {
		Packet.loginOut = loginOut;
	}

	public static Map<Integer, Class<? extends PacketIn>> getPlayIn() {
		return playIn;
	}

	public static void setPlayIn(Map<Integer, Class<? extends PacketIn>> playIn) {
		Packet.playIn = playIn;
	}

	public static Map<Class<? extends PacketOut>, Integer> getPlayOut() {
		return playOut;
	}

	public static void setPlayOut(Map<Class<? extends PacketOut>, Integer> playOut) {
		Packet.playOut = playOut;
	}

}
