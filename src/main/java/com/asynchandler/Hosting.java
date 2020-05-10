package com.asynchandler;

public class Hosting {

    private Integer Id;
    private String name;
    private long websites;
    
    

    public Hosting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hosting(int id, String name, long websites) {
        this.Id = id;
        this.name = name;
        this.websites = websites;
    }

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWebsites() {
		return websites;
	}

	public void setWebsites(long websites) {
		this.websites = websites;
	}

	@Override
	public String toString() {
		return "Hosting [Id=" + Id + ", name=" + name + ", websites=" + websites + "]";
	}
	
	
    
    
    
    
    

    //getters, setters and toString()
}