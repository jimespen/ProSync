package com.github.prosync.communication;

import com.github.prosync.domain.Camera;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTest {
	Controller controller;
	Camera camera;
	File file = new File("testfile.mp4");

	@Before
	public void setUp() throws Exception {
		controller = new Controller();
	}

	@Test
	public void testGetRequest() throws Exception {
		assertTrue(controller.getRequest(new URL("http://10.5.5.9/camera/SH?t=testtest&p=%01")));
	}

	@Test
	public void testGetFileHTTP() throws Exception {
		camera = mock(Camera.class);
		when(controller.getFileHTTP(new URL("http://10.5.5.9:8080/DCIM/105GOPRO/testfile.mp4"), file)).thenReturn(true);
	}
}