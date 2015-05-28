package dataservice;

import java.util.ArrayList;

import po.FramePO;

public interface FrameDataService {
   public void insert(FramePO fp);
   
   public void update(FramePO fp);
   
   public FramePO find(int index);
   
   public ArrayList<FramePO> finds();
   
   public void delete(int index);
}
