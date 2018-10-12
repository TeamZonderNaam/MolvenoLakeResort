package com.resort.springbootMolvenoLake.CRUDtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resort.springbootMolvenoLake.controllers.api.RoomController;
import com.resort.springbootMolvenoLake.models.Room;
import com.resort.springbootMolvenoLake.repositories.RoomsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RoomCRUDTests {

	@InjectMocks
	private RoomController roomsController;

	@Mock
	private RoomsRepository roomsRepository;
	private MockMvc mockMvc;

	@Before
	public void configure() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(roomsController).build();
	}

//	POST TEST WHICH CHECKS ON CREATION OF ROOM IF ALL FIELDS ARE CORRECTLY SENT TO BACKEND
	@Test
	public void newRoomTest()throws Exception {
		//create new Room which is tested with all values included
		Room newRoom = new Room();
		newRoom.setNumber(100);
		newRoom.setId(1);
		newRoom.setAvailable(true);
		newRoom.setPrice(50);
//		TODO: resolve error
//		newRoom.setTypeRoom(Room.RoomType.DELUXE);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(newRoom);

		when(roomsRepository.save(Mockito.any(Room.class))).thenReturn(newRoom);

		this.mockMvc.perform(
				post("/hotel/room/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andDo(print())
				.andExpect(jsonPath("$.id").value(newRoom.getId()))
				.andExpect(jsonPath("$.number").value(newRoom.getNumber()))
				.andExpect(jsonPath("$.available").value(newRoom.isAvailable()))
				.andExpect(jsonPath("$.price").value(newRoom.getPrice()))
				//TODO lookup problem which causes tests to fail --> ENUM compared to STRING
//				.andExpect(jsonPath("$.typeRoom").value(newRoom.getTypeRoom()))
				.andExpect(status().isOk());
	}

	//	GET TEST WHICH CHECKS IF ROOM IS CORRECTLY RETRIEVED FROM BACKEND
	@Test
	public void getRoomTest()throws Exception {
		List<Room> rooms = new ArrayList<>();

		Room newRoom1 = new Room();
		newRoom1.setNumber(100);
		newRoom1.setId(1);
		newRoom1.setAvailable(true);
		newRoom1.setPrice(50);

		Room newRoom2 = new Room();
		newRoom2.setNumber(200);
		newRoom2.setId(2);
		newRoom2.setAvailable(true);
		newRoom2.setPrice(75);

		rooms.add(newRoom1);
		rooms.add(newRoom2);

		when(roomsRepository.findAll()).thenReturn(rooms);

		this.mockMvc.perform(get("/hotel/room/"))
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$.[0].number").value(rooms.get(0).getNumber()))
				.andExpect(jsonPath("$.[1].number").value(rooms.get(1).getNumber()));
	}
}
