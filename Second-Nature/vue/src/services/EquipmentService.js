import axios from "axios";

export default {
    /**
     * Returns a list of equipment and their details.
     * @returns List<Equipment>
     * status: 200 OK
     */
    getAllEquipment() {
        return axios.get('/equipment/get-all');
    },

    /**
     * Returns all the details of a piece of equipment
     * @param {equpmentId} id 
     * @returns an Equipment
     * status: 200 OK
     */
    getEquipmentById(id) {
        return axios.get(`/equipment/${id}`);
    },
    
    /**
     * Returns a list of equipment serial numbers, models, and names.
     * @returns List of EquipmentIdentity
     * status: 200 OK
     */
    getAllEquipmentIdentity() {
        return axios.get('/equipment/identity-list');
    },

    /**
     * Inserts a new equipment record into the database. 
     * Returns the new equipment object
     * @param {createdEquipment} createdEquipment 
     * @returns an Equipment
     * status: 201 CREATED
     */
    createEquipment(createdEquipment){
        return axios.create('/equipment/create', createdEquipment);
    },

    /**
     * Updates an existing equipment record and 
     * returns the updated equipment object.
     * @param {updateEquipment} updatedEquipment 
     * @returns an Equipment
     * status: 202 ACCEPTED
     */
    updateEquipment(updatedEquipment){
        return axios.post('/equipment/update',updatedEquipment);
    },

    /**
     * Updates an existing equipment record's activity status and 
     * returns the updated equipment object.
     * @param {updateEquipmentActivity} updateEquipmentActivity 
     * @returns an Equipment
     * status: 202 ACCEPTED
     */
    updateEquipmentActivity(updateEquipmentActivity){
        return axios.post('/equipment/update-activity', updateEquipmentActivity);
    },

    /**
     * Updates and existing equipment records archive status and 
     * returns the updated equipment object.
     * @param {archiveEquipment} archiveEquipment 
     * @returns an Equipment
     * status: 202 ACCEPTED
     */
    archiveEquipment(archiveEquipment) {
        return axios.post('/equipment/archive', archiveEquipment);
    },

    /**
     * Deletes an existing equipment record.
     * @param {equpmentId} id 
     * @returns 
     * nothing- no status?
     */
    deleteEquipment(id){
        return axios.delete(`/equipment/delete/${id}`);
    }

}

/**
 * Pasted for reference- 
 * JSON Objects:
Equipment
{
	"equipmentId" : 0,
	"serialNumber" : "Equipment Serial Number",
	"model" : "Equipment Model",
	"name" : "Equipment Name",
	"startingHours" : 0,
	"enteredByUserId" : 0, // Can be left blank, updated on the server
	"enteredOnDate" : "2024-08-08", // Can be left blank, updated on the server
	"notes" : "Equipment Notes",
	"isActive" : true,
	"activeNotes" : "Equipment active notes",
	"updatedByUserId" : 0, // Can be left blank, updated on the server
	"updatedOnDate" : "2024-08-08", // Can be left blank, updated on the server
	"archived" : false
}

Equipment Identity
{
	"equipmentId" : 0,
	"serialNumber" : "Equipment Serial Number",
	"model" : "Equipment Model",
	"name" : "Equipment Name",
}

Archive Equipment DTO
{
	"equipmentId" : 0,
	"updatedByUserId" : 0, // Can be left blank, updated on the server
	"archived" : true
}

Create Equipment DTO
{
	"serialNumber" : "Equipment Serial Number",
	"model" : "Equipment Model",
	"name" : "Equipment Name",
	"startingHours" : 0,
	"enteredByUserId" : 0, // Can be left blank, updated on the server
	"notes" : "Equipment Notes",
	"active" : true,
	"activeNotes" : "Equipment active notes",
	"archived" : false
}

Update Equipment Activity DTO
{
	"equipmentId" : 0,
	"active" : true,
	"activeNotes" : "Equipment active notes",
	"updatedByUserId" : 0 // Can be left blank, updated on the server
}

Update Equipment DTO
{
	"equipmentId" : 0,
	"serialNumber" : "Equipment Serial Number",
	"model" : "Equipment Model",
	"name" : "Equipment Name",
	"notes" : "Equipment Notes",
	"active" : true,
	"activeNotes" : "Equipment active notes",
	"updatedByUserId" : 0, // Can be left blank, updated on the server
	"archived" : false
}
 */