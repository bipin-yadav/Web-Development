package org.tlp.bean;

import java.net.InetAddress;

public class IPBean {

	private String converter = "10.203.33.116:8080";
	private String folder = "Videos";
	
	private String videoURL = "http://" + getIP() + ":8080/Videos/";
	private String imageURL = "http://" + getIP() + ":8080/Image/";
	private String defURL = "http://" + getIP() + ":8080/Videos/EUW.m3u8";

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getDefURL() {
		return defURL;
	}

	public void setDefURL(String defURL) {
		this.defURL = defURL;
	}

	public String getConverter() {
		return converter;
	}

	public void setConverter(String converter) {
		this.converter = converter;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public String getIP() {

		/*String str = null;
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			str = ipAddr.getHostAddress();
			//System.out.println(str);
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/

		return "localhost";
	}

}
