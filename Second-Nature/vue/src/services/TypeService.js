import axios from "axios";

export default {
    /**
     * Creates a new type.
     * Expects a Type DTO JSON object
     * @returns a Type JSON object
     */
    createNewType(newTypeDTO) {
        return axios.put('/type/create', newTypeDTO);
    },
    /**
     * Gets a type by it's ID.
     * Expects a typeId in the path
     * @returns a Type JSON object
     */
    getTypeById(typeId) {
        return axios.get(`/type/${typeId}`);
    },
    /**
     * Gets all types.
     * @returns an array of Type JSON object
     */
    getAllTypes() {
        return axios.get('/type/all');
    },
    /**
     * Updates a type.
     * Expects a Type JSON object
     * @returns a Type JSON object
     */
    updateType(updatedType) {
        return axios.post('/type/update', updatedType);
    },
    /**
     * Deletes a type.
     * Expects a typeId in the path
     * @returns nothing
     */
    deleteType(typeId) {
        return axios.put(`/type/${typeId}`);
    }
}

/**
 * JSON Objects
Type
{
	"typeId" : -1,
	"name" : "Type Name"
}

Type DTO
{
	"name" : "Type Name"
}
 */
