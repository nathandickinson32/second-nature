import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import NewRequestView from  '../views/LEAVE-REQUEST/NewRequestView.vue';
import MyLeaveRequestsView from '../views/LEAVE-REQUEST/MyLeaveRequestsView.vue';
import ReviewLeaveRequestsView from '../views/LEAVE-REQUEST/ReviewLeaveRequestsView.vue';
import KudosView from '../views/KUDOS/KudosView.vue';
import KudoDetailView from '../views/KUDOS/KudoDetailView.vue';
import NewKudoView from '../views/KUDOS/NewKudoView.vue';
import ProfessionalCheckInView from '../views/CHECK-INS/MyProfessionalCheckInView.vue';
import ProfessionalCheckInDetails from '../views/CHECK-INS/ProfessionalCheckInDetailView.vue';
import EquipmentListView from '../views/EQUIPMENT/EquipmentListView.vue';
import NewProfessionalCheckIn from '../views/CHECK-INS/NewProfessionalCheckInView.vue';
import EquipmentDetailView from '../views/EQUIPMENT/EquipmentDetailView.vue'; 
import MaintenanceTicketListView from '../views/MAINTENANCE/MaintenanceTicketListView.vue';
import MaintenanceTicketDetailView from '../views/MAINTENANCE/MaintenanceTicketDetailView.vue'; 
import NewMaintenenceTicketView from '../views/MAINTENANCE/NewMaintenenceTicketView.vue';
import MaintenanceTicketDetails from '../components/MAINTENANCE/MaintenanceTicketDetails.vue';
import CreateEquipmentView from '../views/EQUIPMENT/CreateEquipmentView.vue';
import CreateMaintenanceTicketView from '../views/MAINTENANCE/CreateMaintenanceTicketView.vue';
import EquipmentDetailView2 from '../views/EQUIPMENT/EquipmentDetailView2.vue';
import EquipmentModifyView from '../views/EQUIPMENT/EquipmentModifyView.vue';
import EquipmentStatusView from '../views/EQUIPMENT/EquipmentStatusView.vue';
import EquipmentDetailArchive from '../views/EQUIPMENT/EquipmentDetailArchive.vue';
import MaintenanceModifyView from '../views/MAINTENANCE/MaintenanceModifyView.vue';
import TimeStampView from '../views/TIME-STAMP/TimeStampView.vue';
import TimeStampClockInView from '../views/TIME-STAMP/TimeStampClockInView.vue';
import TimeStampClockOutView from '../views/TIME-STAMP/TimeStampClockOutView.vue';
import TimeStampMealInView from '../views/TIME-STAMP/TimeStampMealInView.vue';
import TimeStampMealOutView from '../views/TIME-STAMP/TimeStampMealOutView.vue';
import TimeCardsView from '../views/TIME-CARD/TimeCardsView.vue';
import TimeCardDetailsView from '../views/TIME-CARD/TimeCardDetailsView.vue'




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
    path: '/equipment2/:equipmentId',
    name: 'equipment-detail2',
    component: EquipmentDetailView2,
    meta: {
      requiresAuth  : true
    }
  },
  {
    path: '/equipmentModify/:equipmentId',
    name: 'equipment-modify',
    component: EquipmentModifyView,
    meta: {
      requiresAuth  : true
    }
  },
  {
    path: '/equipmentStatus/:equipmentId',
    name: 'equipment-status',
    component: EquipmentStatusView,
    meta: {
      requiresAuth  : true
    }
  },
  {
    path: '/equipmentArchive/:equipmentId',
    name: 'equipment-archive',
    component: EquipmentDetailArchive,
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
    path: '/maintenance-ticket/modify/:ticketId',
    name: 'maintenance-ticket-modify',
    component: MaintenanceModifyView,
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
  
  },
  {
    path: '/time-stamp',
    name: 'time-stamp',
    component: TimeStampView,
    meta: {
      requiresAuth: false
    }
  
  },
  {
    path: '/time-stamp/clock-in',
    name: 'clock-in',
    component: TimeStampClockInView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: '/time-stamp/clock-out',
    name: 'clock-out',
    component: TimeStampClockOutView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: '/time-stamp/meal-in',
    name: 'meal-in',
    component: TimeStampMealInView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: '/time-stamp/meal-out',
    name: 'meal-out',
    component: TimeStampMealOutView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: '/time-card',
    name: 'time-card',
    component: TimeCardsView,
    meta:{
      requiresAuth: true
    }
  },
  {
    path: '/time-card/:timeCardId',
    name: 'time-card-detail',
    component: TimeCardDetailsView,
    meta:{
      requiresAuth: true
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
