import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { routes } from './routes'
import PageNotFound from '../pages/PageNotFound'
import Home from '../pages/Home'
import AboutUs from '../pages/AboutUs'
import FAQ from '../pages/FAQ'
import Blog from '../pages/Blog'
import Register from '../pages/Register'
import LogIn from '../pages/LogIn'
import Profile from '../pages/Profile'
import Habits from '../pages/Habits'
import AuthGuard from './AuthGuard'
import Header from '../components/layout/Header'
import Footer from '../components/layout/Footer'

export default function AppRoutes() {
    return (
        <div className='flex flex-col min-h-screen text-dark dark:text-light bg-light dark:bg-dark'>
            <BrowserRouter className='flex-1 '>
                <Header />
                <div className='flex-1'>
                    <Routes>
                        <Route path='*' element={<PageNotFound />} />
                        <Route path={routes.public.HOME} element={<Home />} />
                        <Route path={routes.public.ABOUT} element={<AboutUs /> } />
                        <Route path={routes.public.FAQ} element={<FAQ /> } />
                        <Route path={routes.public.BLOG} element={<Blog /> } />
                        <Route path={routes.public.REGISTER} element={<Register />} />
                        <Route path={routes.public.LOGIN} element={<LogIn />} />

                        {/* Guard para que los usuarios sin sesión iniciada no ingresen a perfil y turnos   */}
                        {1 == 10 && <Route element={<AuthGuard />}>
                            <Route path={routes.private.PROFILE} element={<Profile />} />
                            <Route path={routes.private.APPOINTMENTS} element={<Habits />} />
                        </Route>}
                    </Routes>
                </div>
                <Footer />
            </BrowserRouter>
        </div>
    )
}
