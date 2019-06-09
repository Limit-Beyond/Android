package com.example.dome2;

public class ThreadA implements Runnable {

    private String result = "";

    private Service service;

    private TYPE.TypeEnum type = TYPE.TypeEnum.NONE;

    private String id = "";

    private boolean completed = false;

    public ThreadA(Service service,TYPE.TypeEnum type,String id ){
        this.service=service;
        this.type=type;
        this.id=id;
    }
    public ThreadA(Service service,TYPE.TypeEnum type ){
        this.service=service;
        this.type=type;
    }

    @Override
    public void run() {
        switch (type){
            case FINDONE :
                result=service.findOne(id);
                break;

            case DELETEONE:
                result=service.deleteOne(id);
                break;

            case FINDALL:
                result=service.findAll();
                break;

            case DELETEALL:
                result=service.deleteAll();
                break;

            case INSERTONE:
                result=service.insertOne(id);
                break;

        }
        setCompleted(true);
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
