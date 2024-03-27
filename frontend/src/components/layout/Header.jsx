import { useState } from 'react'
import { NavLink } from 'react-router-dom';
import Logo from '../../assets/froggy-logo.svg';
import { links } from '../../routes/routes';
import { MdMenu, MdClose, MdOutlineAccountCircle } from 'react-icons/md';

export default function Header() {

    const [open, setOpen] = useState(false);

    return (
        <div className='font-title font-bold flex flex-row items-center justify-between px-2 md:px-4 lg:px-16 xl:px-20 text-sm lg:text-normal xl:text-lg'>
            <NavLink to={links.anchors[0].url} className='bg-light dark:bg-dark z-10 block w-full md:w-fit hover:scale-105 duration-300'>
                <img src={Logo} alt='Dabits Logo' className='h-[90px]' />
            </NavLink>
            <div className={`z-60 md:flex md:items-center md:gap-x-4 lg:gap-x-6 my-2 absolute md:static left-0 w-full md:w-auto md:pl-0 transition-all duration-1000 ease-in font-normal 
                ${open ? 'top-16' : 'top-[-490px]'}`}>
                {links.anchors.map((link) => {
                    return (
                        <NavLink key={link.url}
                            to={link.url}
                            onClick={() => setOpen(false)}
                            className='bg-light dark:bg-dark block py-3 md:py-4 hover:text-primary text-xl lg:text-normal xl:text-lg text-center duration-500'>
                            {link.name}
                        </NavLink>)
                })}
                <div className='md:ms-10 md:me-2 flex flex-col md:flex-row items-center justify-center md:justify-start gap-2 text-xl lg:text-normal xl:text-lg text-center'>
                    {links.buttons.map((link) => {
                        return (
                            <NavLink key={link.url}
                                to={link.url}
                                onClick={() => setOpen(false)}
                                className='block px-4 py-2 rounded-3xl border border-primary shadow-md shadow-primary/30 hover:shadow-lg hover:shadow-primary/40 hover:scale-105 duration-300'>
                                {link.name}
                            </NavLink>)
                    })}
                </div>
            </div>
            <div className='z-30 px-2 flex flex-row items-center gap-6 text-grey5 text-4xl'>
                <div onClick={() => setOpen(!open)} className='w-fit cursor-pointer block md:hidden'>
                    {
                        open ? <MdClose /> : <MdMenu />
                    }
                </div>
                <div className='border-l-2 ps-4'>
                    <MdOutlineAccountCircle />
                </div>
            </div>
        </div>
    )
}
