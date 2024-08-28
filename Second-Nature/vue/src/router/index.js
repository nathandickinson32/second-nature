import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import NewRequestView from  '../views/NewRequestView.vue';
import MyLeaveRequestsView from '../views/MyLeaveRequestsView.vue';
import ReviewLeaveRequestsView from '../views/ReviewLeaveRequestsView.vue';
import KudosView from '../views/KudosView.vue';
import KudoDetailView from '../views/KudoDetailView.vue';
import NewKudoView from '../views/NewKudoView.vue';
import ProfessionalCheckInView from '../views/MyProfessionalCheckInView.vue';
import ProfessionalCheckInDetails from '../views/ProfessionalCheckInDetailView.vue';
import EquipmentListView from '../views/EquipmentListView.vue';
import NewProfessionalCheckIn from '../views/NewProfessionalCheckInView.vue';
import EquipmentDetailView from '../views/EquipmentDetailView.vue'; 
import MaintenanceTicketListView from '../views/MaintenanceTicketListView.vue';
import MaintenanceTicketDetailView from '../views/MaintenanceTicketDetailView.vue'; 
import NewMaintenenceTicketView from '../views/NewMaintenenceTicketView.vue';
import MaintenanceTicketDetails from '../components/MAINTENANCE/MaintenanceTicketDetails.vue';
import CreateEquipmentView from '../views/CreateEquipmentView.vue';
import CreateMaintenanceTicketView from '../views/CreateMaintenanceTicketView.vue';
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/new-request',
    name: 'newPTOrequest',
    component: NewRequestView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/my-requests',
    name: 'myRequests',
    component: MyLeaveRequestsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/kudos',
    name: 'kudos',
    component: KudosView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/reviewLeaveRequests",
    name: "reviewLeaveRequests",
    component: ReviewLeaveRequestsView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/kudos/:kudosId",
    name: "kudosDetail",
    component: KudoDetailView,
    props: true,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/kudos/new",
    name: "newKudo",
    component: NewKudoView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/my-check-ins',
    name: 'myCheckIns',
    component: ProfessionalCheckInView,
   
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/check-in-details/:checkInId',
    name: 'check-in-details',
    component: ProfessionalCheckInDetails,
    props: true,
    meta: {
      requiresAuth: true
    }
  },
  
  {
    path: '/new-check-in',
    name: 'newProfessionalCheckIn',
    component: NewProfessionalCheckIn,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/equipment',
    name: 'equipmentList',
    component: EquipmentListView,
    meta: {
      requiresAuth  : true
    }
  },
  {
    path: '/create-equipment',
    name: 'create-equipment',
    component: CreateEquipmentView,
    meta: {
      requiresAuth : true
    }
  },
  {
    path: '/equipment/:equipmentId',
    name: 'equipment-detail',
    component: EquipmentDetailView,
    meta: {
      requiresAuth  : true
    }
  },
  {
    path: '/maintenance-ticket-list/',
    name: 'maintenance-ticket-List',
    component: MaintenanceTicketListView,
    meta: {
      requiresAuth  : true
    }
  },
  {
    path: '/maintenance-ticket/create',
    name: 'create-maintenance-ticket',
    component: CreateMaintenanceTicketView,
    meta : {
      requiresAuth : true
    }
  },
  {
    path: '/maintenance-ticket-list/:ticketId',
    name: 'maintenance-ticket-detail',
    component: MaintenanceTicketDetailView,
    meta: {
      requiresAuth  : true
    }
  },
  {
    path: '/new-maintenance-ticket/:equipment.id',
    name: 'new-maintenance-ticket',
    component: NewMaintenenceTicketView,
    meta: {
      requiresAuth  : true
    }
  
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
