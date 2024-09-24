package md.hajji.equipmentservice.enums;

public enum EquipmentStatus {
    /**
     * the equipment is ready to use
     */
    AVAILABLE,
    /**
     * if the equipment is no longer available due to operational issues
     */
    OUT_OF_SERVICE,
    /**
     * if the equipment is no longer exists or available in lab
     */
    DECOMMISSIONED,
}
